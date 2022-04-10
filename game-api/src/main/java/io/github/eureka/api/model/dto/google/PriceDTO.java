package io.github.eureka.api.model.dto.google;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Project: MSA-springboot-eureka.<br/>
 * Des: <br/>
 * User: HieuTT<br/>
 * Date: 07/04/2022<br/>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PriceDTO {
    private String priceMicros;
    private String currency;
}
