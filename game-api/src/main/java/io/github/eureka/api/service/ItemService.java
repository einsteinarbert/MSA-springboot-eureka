package io.github.eureka.api.service;

import io.github.eureka.api.model.dto.SaleInfoDTO;

/**
 * Project: MSA-springboot-eureka.<br/>
 * Des: <br/>
 * User: HieuTT<br/>
 * Date: 03/04/2022<br/>
 * Time: 13:28<br/>
 */
public interface ItemService {
    boolean buyProduct(SaleInfoDTO saleInfo);
}
