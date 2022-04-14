package io.github.eureka.api.service;

import io.github.eureka.api.model.dto.PurchaseDTO;
import io.github.eureka.api.model.dto.SaleInfoDTO;
import io.github.eureka.api.model.entity.ProductInfoDTO;
import org.json.JSONException;

import java.io.IOException;
import java.util.List;

/**
 * Project: MSA-springboot-eureka.<br/>
 * Des: <br/>
 * User: HieuTT<br/>
 * Date: 03/04/2022<br/>
 * Time: 13:28<br/>
 */
public interface ItemService {
    boolean buyProduct(SaleInfoDTO saleInfo);
    boolean purchase(PurchaseDTO saleInfo) throws JSONException, IOException, IllegalAccessException;

    /**
     * get list saleable product
     * @param prodId: optional
     * @return list
     */
    List<ProductInfoDTO> getListProducts (Long prodId);
}
