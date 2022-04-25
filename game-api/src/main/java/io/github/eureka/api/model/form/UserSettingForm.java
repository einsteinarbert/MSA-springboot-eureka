package io.github.eureka.api.model.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

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
    @NotNull(message = "characterId.null")
    private Long characterId;
    @NotNull(message = "backgroundId.null")
    private Long backgroundId;
}
