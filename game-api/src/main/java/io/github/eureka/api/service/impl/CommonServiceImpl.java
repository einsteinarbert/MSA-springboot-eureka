package io.github.eureka.api.service.impl;

import io.eventuate.common.json.mapper.JSonMapper;
import io.github.eureka.api.common.Constant;
import io.github.eureka.api.common.MsgUtil;
import io.github.eureka.api.model.UserWalletHistories;
import io.github.eureka.api.model.UserWallets;
import io.github.eureka.api.model.Users;
import io.github.eureka.api.model.Wallets;
import io.github.eureka.api.model.dto.ActionUserDTO;
import io.github.eureka.api.model.dto.PurchaseDTO;
import io.github.eureka.api.model.dto.google.SubscriptionPurchaseDTO;
import io.github.eureka.api.repo.UserWalletHistoriesRepository;
import io.github.eureka.api.repo.UserWalletsRepository;
import io.github.eureka.api.repo.UsersRepository;
import io.github.eureka.api.repo.WalletsRepository;
import io.github.eureka.api.service.CommonService;
import lombok.AllArgsConstructor;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.transaction.Transactional;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;

/**
 * Project: MSA-springboot-eureka.<br/>
 * Des: <br/>
 * User: HieuTT<br/>
 * Date: 07/04/2022<br/>
 * Time: 17:04<br/>
 */
@AllArgsConstructor
 @Service
public class CommonServiceImpl implements CommonService {
    @PersistenceContext
    EntityManager em;
    private final OkHttpClient client = new OkHttpClient();
    private final UsersRepository usersRepository;
    private final WalletsRepository walletsRepository;
    private final UserWalletsRepository userWalletsRepository;
    private final UserWalletHistoriesRepository userWalletHistoriesRepository;
    
    private static final String sqlCheckBalanceEnought = "select sum(uw.number) currency \n" +
            "from user_wallets uw \n" +
            "inner join wallets w ON uw.wallet_id = w.id and IF(:paymentMethodType = 0, 0, 1) = w.wallet_type where user_id = :userId group by uw.user_id";
    private static final String sqlGetJewelBonus = "select sum(uw.number) currency \n" +
            "from user_wallets uw \n" +
            "inner join wallets w ON uw.wallet_id = w.id and 0 = w.wallet_type and w.jewel_type = 1 where user_id = :userId group by uw.user_id";

    @Override
    public Object verifyInAppPurchase(PurchaseDTO transInfo) throws IllegalAccessException, IOException, JSONException {
        if (Constant.PlatformType.ANDROID.getType() == transInfo.getPlatformType()) {
            String url = Constant.PLAY_STORE_TRANS_URL;
            url = url.replace("{packageName}", transInfo.getPackageName());
            url = url.replace("{subscriptionId}", transInfo.getSubscriptionId());
            url = url.replace("{token}", transInfo.getTokenProduct());
            Request request = new Request.Builder()
                    .url(url)
                    .get()
                    .build();

            Call call = client.newCall(request);
            Response response = call.execute();
            assert response.body() != null;
            return JSonMapper.fromJson(new JSONObject(response.body().toString()).getString("resource"),
                    SubscriptionPurchaseDTO.class);

        } else if (Constant.PlatformType.IOS.getType() == transInfo.getPlatformType()) {
            // TODO
        }
        throw new IllegalAccessException(MsgUtil.getMessage("base.service.wrong.platform"));
    }

    public Users validateUser(ActionUserDTO userDTO) {
        Assert.notNull(userDTO, MsgUtil.getMessage("user.info.null"));
        Users user = usersRepository.findByUsernameAndStatusIn(userDTO.getSub(), Arrays.asList(0, 1)).orElseThrow (
                () -> new IllegalArgumentException(MsgUtil.getMessage("user.info.null"))
        );
        Assert.isTrue(user.getUsername().equals(userDTO.getSub()),
                MsgUtil.getMessage("user.invalid", user.getUsername()));
        return user;
    }

    @Override
    public Boolean checkBalanceEnought(Long userId, String platform, Integer paymentMethodType, Integer price) {
        Integer balance = ((Number) em.createNativeQuery(sqlCheckBalanceEnought)
        .setParameter("userId", userId)
        .setParameter("paymentMethodType", paymentMethodType)
        .getSingleResult()).intValue();
        
        if (balance < price)
            return false;
        else 
            return true;
    }

    @Override
    @Transactional
    public void changeBalanceProgress(Long userId, Integer paymentMethodId, Integer price) {
        //Cap nhat history
        if(Constant.PaymentMethodType.JEWEL.getType() == paymentMethodId) {
            if (this.checkJewelBonusEnought(userId, price)) {
                Wallets walletsUpdate = walletsRepository.findWalletsByWalletTypeAndJewelType(Constant.WalletType.JEWEL.getType(), Constant.JewelType.BONUS.getType());
                UserWalletHistories userWalletHistories = new UserWalletHistories();
                userWalletHistories.setUserId(userId);
                userWalletHistories.setWalletId(walletsUpdate.getId());
                userWalletHistories.setNumber(price);
                userWalletHistories.setSupplyNumber(1);
                userWalletHistories.setMessage("BUY-GACHA");
                userWalletHistories.setTransNumber("123456789");
                userWalletHistories.setHistoryType(Constant.walletHistoryType.USE.getType());
                userWalletHistories.setCreatedAt(new Date());
                userWalletHistories.setUpdatedAt(new Date());
                userWalletHistoriesRepository.save(userWalletHistories);

                UserWallets userWallets = userWalletsRepository.findUserWalletsByUserIdAndWalletId(userId, walletsUpdate.getId());
                userWallets.setNumber(userWallets.getNumber() - price);
                userWalletsRepository.save(userWallets);
            }else{
                if(this.jewelBonus(userId) == 0){
                    Wallets walletsJewelUpdate = walletsRepository.findWalletsByWalletTypeAndJewelType(Constant.WalletType.JEWEL.getType(), Constant.JewelType.BUY.getType());
                    UserWallets userJewelWallets = userWalletsRepository.findUserWalletsByUserIdAndWalletId(userId, walletsJewelUpdate.getId());
                    
                    UserWalletHistories userWalletHistories = new UserWalletHistories();
                    userWalletHistories.setUserId(userId);
                    userWalletHistories.setWalletId(walletsJewelUpdate.getId());
                    userWalletHistories.setNumber(userJewelWallets.getNumber() - price);
                    userWalletHistories.setMessage("BUY-GACHA");
                    userWalletHistories.setTransNumber("123456789");
                    userWalletHistories.setSupplyNumber(1);
                    userWalletHistories.setHistoryType(Constant.walletHistoryType.USE.getType());
                    userWalletHistories.setCreatedAt(new Date());
                    userWalletHistories.setUpdatedAt(new Date());
                    userWalletHistoriesRepository.save(userWalletHistories);
                    
                    userJewelWallets.setNumber(userJewelWallets.getNumber() - price);
                    userWalletsRepository.save(userJewelWallets);
                }else{
                    Wallets walletsJewelBonus = walletsRepository.findWalletsByWalletTypeAndJewelType(Constant.WalletType.JEWEL.getType(), Constant.JewelType.BONUS.getType());
                    UserWallets userJewelBonusWallets = userWalletsRepository.findUserWalletsByUserIdAndWalletId(userId, walletsJewelBonus.getId());

                    UserWalletHistories userWalletBonusHistories = new UserWalletHistories();
                    userWalletBonusHistories.setUserId(userId);
                    userWalletBonusHistories.setWalletId(walletsJewelBonus.getId());
                    userWalletBonusHistories.setNumber(userJewelBonusWallets.getNumber());
                    userWalletBonusHistories.setHistoryType(Constant.walletHistoryType.USE.getType());
                    userWalletBonusHistories.setMessage("BUY-GACHA");
                    userWalletBonusHistories.setTransNumber("123456789");
                    userWalletBonusHistories.setSupplyNumber(1);
                    userWalletBonusHistories.setCreatedAt(new Date());
                    userWalletBonusHistories.setUpdatedAt(new Date());
                    userWalletHistoriesRepository.save(userWalletBonusHistories);
                    price = price - userJewelBonusWallets.getNumber();
                    userJewelBonusWallets.setNumber(0);
                    userWalletsRepository.save(userJewelBonusWallets);
                    //Update jewel buy
                    Wallets walletsJewel = walletsRepository.findWalletsByWalletTypeAndJewelType(Constant.WalletType.JEWEL.getType(), Constant.JewelType.BUY.getType());
                    UserWallets userJewelWallets = userWalletsRepository.findUserWalletsByUserIdAndWalletId(userId, walletsJewel.getId());

                    UserWalletHistories userWalletHistories = new UserWalletHistories();
                    userWalletHistories.setUserId(userId);
                    userWalletHistories.setWalletId(walletsJewel.getId());
                    userWalletHistories.setNumber(price);
                    userWalletHistories.setSupplyNumber(1);
                    userWalletHistories.setMessage("BUY-GACHA");
                    userWalletHistories.setTransNumber("123456789");
                    userWalletHistories.setHistoryType(Constant.walletHistoryType.USE.getType());
                    userWalletHistories.setCreatedAt(new Date());
                    userWalletHistories.setUpdatedAt(new Date());
                    userWalletHistoriesRepository.save(userWalletHistories);

                    userJewelWallets.setNumber(userJewelWallets.getNumber() - price);
                    userWalletsRepository.save(userJewelWallets);
                }
            }
        }else {
            Wallets walletsUpdate = walletsRepository.findWalletsByWalletType(Constant.WalletType.COIN.getType());
            UserWalletHistories userWalletHistories = new UserWalletHistories();
            userWalletHistories.setUserId(userId);
            userWalletHistories.setWalletId(walletsUpdate.getId());
            userWalletHistories.setNumber(price);
            userWalletHistories.setMessage("BUY-GACHA");
            userWalletHistories.setSupplyNumber(1);
            userWalletHistories.setTransNumber("123456789");
            userWalletHistories.setHistoryType(Constant.walletHistoryType.USE.getType());
            userWalletHistories.setCreatedAt(new Date());
            userWalletHistories.setUpdatedAt(new Date());
            userWalletHistoriesRepository.save(userWalletHistories);

            UserWallets userWallets = userWalletsRepository.findUserWalletsByUserIdAndWalletId(userId, walletsUpdate.getId());
            userWallets.setNumber(userWallets.getNumber() - price);
            userWalletsRepository.save(userWallets);
        }
    }

    @Override
    public Boolean checkJewelBonusEnought(Long userId, Integer price) {
        Integer balance = ((Number) em.createNativeQuery(sqlGetJewelBonus)
                .setParameter("userId", userId)
                .getSingleResult()).intValue();

        if (balance < price)
            return false;
        else
            return true;
    }

    @Override
    public Integer jewelBonus(Long userId) {
        Integer balance = ((Number) em.createNativeQuery(sqlGetJewelBonus)
                .setParameter("userId", userId)
                .getSingleResult()).intValue();
    return balance;
    }
}
