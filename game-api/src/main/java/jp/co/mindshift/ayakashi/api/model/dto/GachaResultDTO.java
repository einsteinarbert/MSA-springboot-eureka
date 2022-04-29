package jp.co.mindshift.ayakashi.api.model.dto;

import jp.co.mindshift.ayakashi.api.model.Characters;
import jp.co.mindshift.ayakashi.api.model.SpecialItems;
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
	private Integer nextLevel = 0;
	private Double probability;
	private Integer skillLevel;
	private String pickup;
	private String memo;
	private Timestamp createdAt;
	private Timestamp updatedAt;
	private Characters characters;
	private SpecialItems medal;
}
