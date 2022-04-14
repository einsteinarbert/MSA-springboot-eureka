package io.github.eureka.api.model.dto;

import io.github.eureka.api.model.entity.ProductInfoEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Project: MSA-springboot-eureka.<br/>
 * Des: <br/>
 * User: HieuTT<br/>
 * Date: 14/04/2022<br/>
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductListDTO {
    List<ProductInfoEntity> listProduct;
    List<Object> productsDetail;
    List<Object> itemsDetail;
}
