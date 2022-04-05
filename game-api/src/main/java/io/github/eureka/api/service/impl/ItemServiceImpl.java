package io.github.eureka.api.service.impl;

import io.github.eureka.api.common.Constant;
import io.github.eureka.api.common.MsgUtil;
import io.github.eureka.api.config.ActionUserHolder;
import io.github.eureka.api.model.ProductPurchaseHistories;
import io.github.eureka.api.model.UserWalletHistories;
import io.github.eureka.api.model.UserWallets;
import io.github.eureka.api.model.Users;
import io.github.eureka.api.model.dto.ActionUserDTO;
import io.github.eureka.api.model.dto.SaleInfoDTO;
import io.github.eureka.api.model.entity.ProductPriceEntity;
import io.github.eureka.api.model.entity.UserWalletEntity;
import io.github.eureka.api.repo.ProductPurchaseHistoriesRepository;
import io.github.eureka.api.repo.UserWalletHistoriesRepository;
import io.github.eureka.api.repo.UserWalletsRepository;
import io.github.eureka.api.repo.UsersRepository;
import io.github.eureka.api.service.ItemService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static io.github.eureka.api.common.HelpUtil.getTransNumber;

/**
 * Project: MSA-springboot-eureka.<br/>
 * Des: <br/>
 * User: HieuTT<br/>
 * Date: 03/04/2022<br/>
 * Time: 13:41<br/>
 */
@Slf4j
@AllArgsConstructor
@Service
public class ItemServiceImpl implements ItemService {
    @PersistenceContext
    private final EntityManager em;

    private final UsersRepository usersRepository;
    private final UserWalletsRepository userWalletsRepository;
    private final UserWalletHistoriesRepository userWalletHistoriesRepository;
    private final ProductPurchaseHistoriesRepository productPurchaseHistoriesRepository;


    @Override
    @Transactional
    public boolean buyItem(SaleInfoDTO saleInfo) {
        ActionUserDTO userDTO = ActionUserHolder.getActionUser();
        Assert.notNull(userDTO, MsgUtil.getMessage("user.info.null"));
        Assert.notNull(saleInfo.getUserId(), MsgUtil.getMessage("sale.trans.info.null"));
        Users user = usersRepository.findById(saleInfo.getUserId()).orElseThrow (
                () -> new IllegalArgumentException(MsgUtil.getMessage("user.info.null"))
        );
        Assert.isTrue(user.getUsername().equals(userDTO.getSub()),
                MsgUtil.getMessage("Username is not valid: {}", user.getUsername()));
        Assert.isTrue(Arrays.stream(Constant.PlatformType.values()).anyMatch(platformType ->
                platformType.getType() == saleInfo.getPlatformType()), MsgUtil.getMessage("sale.trans.info.platform.invalid"));
        // find user wallet
        List<UserWalletEntity> userWallets = em.createNativeQuery(UserWalletEntity.SQL, UserWalletEntity.class)
                .setParameter("user_id", user.getId())
                .setParameter("platform_type", saleInfo.getPlatformType())
                .setParameter("pay_type", saleInfo.getProductInfo().getPayType())
                .getResultList();
        Assert.isTrue(!CollectionUtils.isEmpty(userWallets), MsgUtil.getMessage("sale.trans.info.wallet.empty"));
        validateTotalAmount(userWallets, saleInfo);
        return true;
    }

    private void validateTotalAmount(List<UserWalletEntity> listWallet, SaleInfoDTO saleInfo) {
        List<ProductPriceEntity> ids = saleInfo.getProductInfo().getItemIds();
        List<ProductPriceEntity> priceInfoLst = new ArrayList<>(ids.size());
        int totalAmount = 0;
        for (ProductPriceEntity id : ids) {
            ProductPriceEntity priceInfo = (ProductPriceEntity) em.createNativeQuery(ProductPriceEntity.SQL,
                            ProductPriceEntity.class)
                    .setParameter("item_id", id.getItemId())
                    .setParameter("number", id.getNumber())
                    .getSingleResult();
            priceInfoLst.add(priceInfo);
            totalAmount += priceInfo.getPrice();
        }

        try {
            // locking wallet and validate totalAmount
            List<UserWallets> userWallets = userWalletsRepository.findAllByIdWithLock(listWallet.parallelStream()
                            .map(UserWalletEntity::getUserId).collect(Collectors.toList())
            );
            Assert.isTrue(!CollectionUtils.isEmpty(userWallets), MsgUtil.getMessage("sale.trans.info.wallet.lock.error"));
            int totalBalance = 0;
            for (var wallet: userWallets) {
                totalBalance += wallet.getNumber();
            }
            Assert.isTrue(totalBalance >= totalAmount,
                    MsgUtil.getMessage("sale.trans.info.balance.not.enough", totalAmount));

            // create transaction and save audit log
            String transNumber = getTransNumber(saleInfo.getUserId());
            List<UserWallets> bonusCoin = new ArrayList<>();
            List<UserWallets> buyCoin = new ArrayList<>();
            // split bonus and purchase wallet
            for (var obj: userWallets) {
                for (var obj1: listWallet) {
                    if (obj.getWalletId() == obj1.getWalletId()) {
                        if (obj1.getJewelType() == Constant.JewelType.BONUS.getType()) {
                            bonusCoin.add(obj);
                        } else {
                            buyCoin.add(obj);
                        }
                    }
                }
            }
            for(var price: priceInfoLst) {
                boolean isBonus = true;
                int index = -1;
                for (int i = 0; i < bonusCoin.size(); i++) {
                    if (bonusCoin.get(i).getNumber() >= price.getNumber()) {
                        index = i;
                        break;
                    }
                }
                if (index == -1) {
                    for (int i = 0; i < buyCoin.size(); i++) {
                        if (buyCoin.get(i).getNumber() >= price.getNumber()) {
                            index = i;
                            isBonus = false;
                            break;
                        }
                    }
                }

                Assert.isTrue(index > -1,
                        MsgUtil.getMessage("sale.trans.info.balance.not.enough", price.getNumber()));
                // withdraw
                UserWallets wallet = isBonus ? bonusCoin.get(index) : buyCoin.get(index);
                wallet.setNumber(wallet.getNumber() - price.getNumber());
                wallet = userWalletsRepository.save(wallet);
                if (isBonus) {
                    bonusCoin.set(index, wallet);
                } else {
                    buyCoin.set(index, wallet);
                }
                ProductPurchaseHistories itemLog = new ProductPurchaseHistories();
                Date now = new Date();
                itemLog.setCreatedAt(now);
                itemLog.setUserId(wallet.getUserId());
                itemLog.setPaymentMethodId(saleInfo.getProductInfo().getPayType());
                itemLog.setPrice(totalAmount);
                itemLog.setTransNumber(transNumber);
                itemLog.setPrice(price.getPrice());
                itemLog.setPaymentMethodId(price.getPaymentMethodId());
                itemLog.setNumber(price.getNumber());
                itemLog.setCurrency(null); // cash pay only
                itemLog.setProductId(price.getProductId());
                itemLog.setProductType(price.getProductType());
                itemLog = productPurchaseHistoriesRepository.save(itemLog);

                UserWalletHistories log = new UserWalletHistories();
                log.setHistoryType(Constant.UserHistoryType.USE.getType());
                log.setNumber(totalAmount);
                log.setGeneratableId(itemLog.getId());
                log.setCreatedAt(now);
                log.setTransNumber(transNumber);
                log.setGeneratableType(price.getProductType());
                log.setMessage("??");
                log.setSupplyNumber(-1);
                log.setUserId(wallet.getUserId());
                log.setWalletId(wallet.getWalletId());
                userWalletHistoriesRepository.save(log);
            }

        } catch (Exception e) {
            log.error("Lock wallet error", e);
            throw new IllegalCallerException(MsgUtil.getMessage("sale.trans.info.wallet.lock.error"));
        }
    }
}
