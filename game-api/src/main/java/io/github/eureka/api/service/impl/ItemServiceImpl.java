package io.github.eureka.api.service.impl;

import io.github.eureka.api.common.Constant;
import io.github.eureka.api.common.MsgUtil;
import io.github.eureka.api.config.ActionUserHolder;
import io.github.eureka.api.model.UserWallets;
import io.github.eureka.api.model.Users;
import io.github.eureka.api.model.dto.ActionUserDTO;
import io.github.eureka.api.model.dto.SaleInfoDTO;
import io.github.eureka.api.repo.UsersRepository;
import io.github.eureka.api.service.ItemService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;

/**
 * Project: MSA-springboot-eureka.<br/>
 * Des: <br/>
 * User: HieuTT<br/>
 * Date: 03/04/2022<br/>
 * Time: 13:41<br/>
 */
@AllArgsConstructor
@Service
public class ItemServiceImpl implements ItemService {
    @PersistenceContext
    private final EntityManager em;
    private final UsersRepository usersRepository;

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
        List<UserWallets> userWallets = em.createNativeQuery(
                "select uw.* from user_wallets uw, wallets w " +
                        "where uw.user_id = :id " +
                        "  and w.platform_type = :platform_type " +
                        "and w.id = uw.id", UserWallets.class)
                .setParameter("id", user.getId()).setParameter("platform_type", saleInfo.getPlatformType())
                .setLockMode(LockModeType.PESSIMISTIC_WRITE)
                .getResultList();
        Assert.isTrue(!CollectionUtils.isEmpty(userWallets), MsgUtil.getMessage("sale.trans.info.wallet.empty"));
        UserWallets userWallet = userWallets.stream().filter(obj -> obj.get)
        Long totalAmount =
        Assert.isTrue(userWallet.getNumber() > 0, MsgUtil.getMessage("sale.trans.info.balance.not.enough", userWallet.getNumber()));
        return false;
    }
}
