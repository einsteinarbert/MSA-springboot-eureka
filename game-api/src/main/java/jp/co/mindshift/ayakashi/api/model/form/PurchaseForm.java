package jp.co.mindshift.ayakashi.api.model.form;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

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
public class PurchaseForm implements Serializable {
    private String packageName;
    private String subscriptionId;
    private String tokenProduct;
    private Integer platformType;
    private Long productId;
    private Integer number;
}
