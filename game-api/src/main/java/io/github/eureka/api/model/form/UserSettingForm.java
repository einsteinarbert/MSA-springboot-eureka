package io.github.eureka.api.model.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Project: MSA-springboot-eureka.<br/>
 * Des: <br/>
 * User: HieuTT<br/>
 * Date: 18/04/2022<br/>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserSettingForm {
    private Long characterId;
    private Long backgroundId;
}