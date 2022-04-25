package io.github.eureka.api.model.form;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SpinGachaForm {
	@NotNull(message = "gachaId must be not null")
	private Long gachaId;
	@NotNull(message = "userId must be not null")
	private Long userId;
	@NotNull(message = "paymentMethod must be not null")
	private Integer paymentMethod;
}
