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
@AllArgsConstructor
@NoArgsConstructor
@Data
public class IntroductoryPriceInfoDTO {
    private String introductoryPriceCurrencyCode;
    private String introductoryPriceAmountMicros;
    private String introductoryPricePeriod;
    private Integer introductoryPriceCycles;
}
