package io.github.eureka.api.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Project: MSA-springboot-eureka.<br/>
 * Des: <br/>
 * User: HieuTT<br/>
 * Date: 03/04/2022<br/>
 * Time: 13:37<br/>
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductInfoDTO {
    private List<Long> items;
    private Integer payType; // payment_type
}
