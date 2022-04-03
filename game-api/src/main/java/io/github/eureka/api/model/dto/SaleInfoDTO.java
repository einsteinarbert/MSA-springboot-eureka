package io.github.eureka.api.model.dto;

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
public class SaleInfoDTO implements Serializable {
    private Long userId;
    private Integer platformType;
    private ProductInfoDTO productInfo;
}
