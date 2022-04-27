package jp.co.mindshift.ayakashi.api.service;

import jp.co.mindshift.ayakashi.api.model.dto.ProductListDTO;
import jp.co.mindshift.ayakashi.api.model.dto.PurchaseDTO;
import jp.co.mindshift.ayakashi.api.model.dto.SaleInfoDTO;
import org.json.JSONException;

import java.io.IOException;

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
     * @param prodId : optional
     * @param productType product table name like: jewel_products
     * @param itemType itemType like: heart, background
     * @return list
     */
    ProductListDTO getListProducts (Long prodId, String productType, String itemType);
}
