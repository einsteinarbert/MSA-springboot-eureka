package io.github.eureka.api.model.dto;

import io.github.eureka.api.model.Background;
import io.github.eureka.api.model.Characters;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDataDTO {
	private String username;
	private String name;
	private Integer age;
	private Long characterId;
	private Long backgroundId;
	private Long jewelNumber;
	private Long jewelBonusNumber;
	private Long coinNumber;
	private Long staminaNumber;
	private Long heart;
	private Long heart30;
	private Long heart60;
	
	private CharacterDTO characters;
	private BackgroundDTO background;
}
