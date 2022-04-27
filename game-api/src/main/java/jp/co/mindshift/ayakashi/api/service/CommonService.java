package jp.co.mindshift.ayakashi.api.service;

import jp.co.mindshift.ayakashi.api.model.Users;
import jp.co.mindshift.ayakashi.api.model.dto.ActionUserDTO;
import jp.co.mindshift.ayakashi.api.model.form.PurchaseForm;
import org.json.JSONException;

import java.io.IOException;

/**
 * Project: MSA-springboot-eureka.<br/>
 * Des: <br/>
 * User: HieuTT<br/>
 * Date: 07/04/2022<br/>
 * Time: 17:02<br/>
 */
public interface CommonService {
    Object verifyInAppPurchase(PurchaseForm transInfo) throws IllegalAccessException, IOException, JSONException;
    Users validateUser(ActionUserDTO userDTO);
    Boolean checkBalanceEnought(Long userId, String platform, Integer paymentMethodType, Integer price);
    void changeBalanceProgress(Long userId, Integer paymentMethodId, Integer price);

    Boolean checkJewelBonusEnought(Long userId, Integer price);

    Integer jewelBonus(Long userId);
}
