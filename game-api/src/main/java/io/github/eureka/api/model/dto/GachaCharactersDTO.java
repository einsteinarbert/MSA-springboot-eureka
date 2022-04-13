package io.github.eureka.api.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GachaCharactersDTO {
	private Long id;
	private Long gachaId;
	private Long characterId;
	private Double probability;
	private Integer skillLevel;
	private String pickup;
	private String memo;
	private Timestamp createdAt;
	private Timestamp updatedAt;
	
}
