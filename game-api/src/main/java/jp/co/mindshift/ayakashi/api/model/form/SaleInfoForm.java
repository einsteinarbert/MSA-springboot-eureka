package jp.co.mindshift.ayakashi.api.model.form;

import jp.co.mindshift.ayakashi.api.model.entity.ProductPriceEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * Project: MSA-springboot-eureka.<br/>
 * Des: <br/>
 * User: HieuTT<br/>
 * Date: 03/04/2022<br/>
 * Time: 13:30<br/>
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SaleInfoForm implements Serializable {
    private List<ProductPriceEntity> productIds;
    private Integer payType; // payment_type
}
