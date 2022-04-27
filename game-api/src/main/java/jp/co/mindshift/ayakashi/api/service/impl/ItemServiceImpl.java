package jp.co.mindshift.ayakashi.api.service.impl;

import jp.co.mindshift.ayakashi.api.common.Constant;
import jp.co.mindshift.ayakashi.api.common.MsgUtil;
import jp.co.mindshift.ayakashi.api.config.ActionUserHolder;
import jp.co.mindshift.ayakashi.api.model.JewelProducts;
import jp.co.mindshift.ayakashi.api.model.ProductPurchaseHistories;
import jp.co.mindshift.ayakashi.api.model.UserWalletHistories;
import jp.co.mindshift.ayakashi.api.model.UserWallets;
import jp.co.mindshift.ayakashi.api.model.Users;
import jp.co.mindshift.ayakashi.api.model.dto.ActionUserDTO;
import jp.co.mindshift.ayakashi.api.model.dto.ProductListDTO;
import jp.co.mindshift.ayakashi.api.model.form.PurchaseForm;
import jp.co.mindshift.ayakashi.api.model.form.SaleInfoForm;
import jp.co.mindshift.ayakashi.api.model.dto.google.SubscriptionPurchaseDTO;
import jp.co.mindshift.ayakashi.api.model.entity.ProductInfoEntity;
import jp.co.mindshift.ayakashi.api.model.entity.ProductPriceEntity;
import jp.co.mindshift.ayakashi.api.model.entity.UserWalletEntity;
import jp.co.mindshift.ayakashi.api.repo.JewelProductsRepository;
import jp.co.mindshift.ayakashi.api.repo.ProductPurchaseHistoriesRepository;
import jp.co.mindshift.ayakashi.api.repo.UserWalletHistoriesRepository;
import jp.co.mindshift.ayakashi.api.repo.UserWalletsRepository;
import jp.co.mindshift.ayakashi.api.service.CommonService;
import jp.co.mindshift.ayakashi.api.service.ItemService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONException;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static jp.co.mindshift.ayakashi.api.common.HelpUtil.getTransNumber;

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
    private final CommonService commonService;
    private final UserWalletsRepository userWalletsRepository;
    private final JewelProductsRepository jewelProductsRepository;
    private final UserWalletHistoriesRepository userWalletHistoriesRepository;
    private final ProductPurchaseHistoriesRepository productPurchaseHistoriesRepository;



    @Override
    @Transactional
    public boolean buyProduct(SaleInfoForm saleInfo) {
        ActionUserDTO userDTO = ActionUserHolder.getActionUser();
        Users user = commonService.validateUser(userDTO);
        // find user wallet
        List<UserWalletEntity> userWallets = em.createNativeQuery(UserWalletEntity.SQL, UserWalletEntity.class)
                .setParameter("user_id", user.getId())
                .setParameter("pay_type", saleInfo.getPayType())
                .getResultList();
        Assert.isTrue(!CollectionUtils.isEmpty(userWallets), MsgUtil.getMessage("sale.trans.info.wallet.empty"));
        validateTotalAmount(userWallets, saleInfo, user);
        return true;
    }

    @Override
    public boolean purchase(PurchaseForm saleInfo) throws JSONException, IOException, IllegalAccessException {
        ActionUserDTO userDTO = ActionUserHolder.getActionUser();
        Users user = commonService.validateUser(userDTO);
        ProductPriceEntity productPriceInfo = (ProductPriceEntity) em.createNativeQuery(ProductPriceEntity.SQL,
                        ProductPriceEntity.class)
                .setParameter("product_id", saleInfo.getProductId())
                .setParameter("number", saleInfo.getNumber())
                .getSingleResult();
        Assert.notNull(productPriceInfo, MsgUtil.getMessage("product.purchase.not.found"));
        String itemTable = productPriceInfo.getProductType();
        String token = "";
        int bonusNum = 0;
        int purchaseNum = 0;
        long walletId = -1L;
        long walletBonusId = -1L;
        if ("jewel_products".equalsIgnoreCase(itemTable)) {
            Optional<JewelProducts> productsOptional = jewelProductsRepository.findByProductIdAndJewelProductToken(saleInfo.getProductId(),
                    saleInfo.getTokenProduct());
            Assert.isTrue(productsOptional.isPresent(), MsgUtil.getMessage("product.purchase.not.found"));
            var product = productsOptional.get();
            token = product.getJewelProductToken();
            bonusNum = product.getBonusNumber();
            purchaseNum = product.getNumber();
            walletId = product.getBonusWalletId();
            walletBonusId = product.getBonusWalletId();
        } else if ("package_products".equalsIgnoreCase(itemTable)) {
            // TODO
        }
        Object verify = commonService.verifyInAppPurchase(saleInfo);
        Date now = new Date();
        String transNumber = getTransNumber(user.getId());
        if (Constant.PlatformType.ANDROID.getType() == saleInfo.getPlatformType()) {
            SubscriptionPurchaseDTO subscript = (SubscriptionPurchaseDTO) verify;
            validatePurchaseInfoAndroid(subscript);
            // TODO: write out to user wallet, user item and write out log

        } else if(Constant.PlatformType.IOS.getType() == saleInfo.getPlatformType()) {
            // TODO IOS
        } else {
            return false;
        }
        return true;
    }

    /**
     * get list saleable product
     *
     * @param prodId : optional
     * @param productType string optional
     * @param itemType string optional
     * @return list
     */
    @Override
    public ProductListDTO getListProducts(Long prodId, String productType, String itemType) {
        String sql = ProductInfoEntity.SQL;
        if (null != prodId) {
            sql += " and p.id = :id";
        }
        if (StringUtils.hasLength(productType)) {
            sql += " and p.product_type = :productType";
        }
        if (StringUtils.hasLength(itemType)) {
            sql += " and ip.item_type = :itemType";
        }
        Query query = em.createNativeQuery(sql, ProductInfoEntity.class);
        if (null != prodId) {
            query = query.setParameter("id", prodId);
        }
        if (StringUtils.hasLength(productType)) {
            query = query.setParameter("productType", productType);
        }
        if (StringUtils.hasLength(itemType)) {
            query = query.setParameter("itemType", itemType);
        }
        List<ProductInfoEntity> infoEntityList = query.getResultList();
        ProductListDTO result = ProductListDTO.builder().listProduct(infoEntityList).build();
        if (StringUtils.hasLength(productType)) {
            Query q1 = em.createNativeQuery("Select * from " + productType +" where product_id in :id");
            List<Long> ids = infoEntityList.parallelStream().map(
                    ProductInfoEntity::getProductId
            ).collect(Collectors.toList());
            result.setProductsDetail(q1.setParameter("id", ids).getResultList());
        }
        if (StringUtils.hasLength(productType)) {
            Query q2 = em.createNativeQuery("Select * from " + itemType +" where item_id in :id");
            List<Long> ids = infoEntityList.parallelStream().map(
                    ProductInfoEntity::getItemId
            ).collect(Collectors.toList());
            result.setItemsDetail(q2.setParameter("id", ids).getResultList());
        }
        return result;
    }

    private void validatePurchaseInfoAndroid(SubscriptionPurchaseDTO subscript) {
        Assert.notNull(subscript.getPaymentState(), MsgUtil.getMessage("payment.state.error"));
        Assert.isTrue(subscript.getPaymentState().equals(Constant.PayStateAndroid.PAYMENT_RECEIVED.getValue()),
                MsgUtil.getMessage("payment.state.invalid", MsgUtil.getMessage(
                        Arrays.stream(Constant.PayStateAndroid.values()).filter(obj ->
                                obj.getValue() == subscript.getPaymentState()).collect(Collectors.toList()).get(0).getText()
                )));
    }

    private void validateTotalAmount(List<UserWalletEntity> listWallet, SaleInfoForm saleInfo, Users user) {
        List<ProductPriceEntity> ids = saleInfo.getProductIds();
        List<ProductPriceEntity> priceInfoLst = new ArrayList<>(ids.size());
        int totalAmount = 0;
        for (ProductPriceEntity id : ids) {
            ProductPriceEntity priceInfo = (ProductPriceEntity) em.createNativeQuery(ProductPriceEntity.SQL,
                            ProductPriceEntity.class)
                    .setParameter("product_id", id.getProductId())
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
            String transNumber = getTransNumber(user.getId());
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
                Date now = new Date();
                UserWallets wallet = isBonus ? bonusCoin.get(index) : buyCoin.get(index);
                wallet.setNumber(wallet.getNumber() - price.getNumber());
                wallet.setUpdatedAt(now);
                wallet = userWalletsRepository.save(wallet);
                if (isBonus) {
                    bonusCoin.set(index, wallet);
                } else {
                    buyCoin.set(index, wallet);
                }
                ProductPurchaseHistories itemLog = new ProductPurchaseHistories();
                itemLog.setCreatedAt(now);
                itemLog.setUserId(wallet.getUserId());
                itemLog.setPaymentMethodId(saleInfo.getPayType());
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
                if (isBonus) {
                    log.setSupplyNumber(totalAmount);
                } else {
                    log.setNumber(totalAmount);
                }
                log.setGeneratableId(itemLog.getId());
                log.setCreatedAt(now);
                log.setTransNumber(transNumber);
                log.setGeneratableType(price.getProductType());
                log.setMessage(MsgUtil.getMessage("sale.trans.info.message", price.getName(), price.getNumber(),
                        Constant.WalletType.getName(saleInfo.getPayType()), totalAmount));
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
