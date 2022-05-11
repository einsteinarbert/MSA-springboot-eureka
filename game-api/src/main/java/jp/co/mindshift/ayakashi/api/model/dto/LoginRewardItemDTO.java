package jp.co.mindshift.ayakashi.api.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginRewardItemDTO {
	private String itemType;
	private Integer amount;
}
