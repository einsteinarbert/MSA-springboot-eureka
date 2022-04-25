package io.github.eureka.api.model.dto;

import io.github.eureka.api.model.Characters;
import io.github.eureka.api.model.SpecialItems;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GachaResultDTO {
	private Long id;
	private Long gachaId;
	private Long characterId;
	private Long itemId;
	private Double probability;
	private Integer skillLevel;
	private String pickup;
	private String memo;
	private Timestamp createdAt;
	private Timestamp updatedAt;
	private Characters characters;
	private SpecialItems specialItems;
}
