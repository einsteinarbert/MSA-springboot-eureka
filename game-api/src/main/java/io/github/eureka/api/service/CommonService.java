package io.github.eureka.api.service;

import io.github.eureka.api.model.dto.PurchaseDTO;
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
    Object verifyInAppPurchase(PurchaseDTO transInfo) throws IllegalAccessException, IOException, JSONException;
}
