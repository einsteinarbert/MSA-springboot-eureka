package jp.co.mindshift.ayakashi.api.model.dto;

import jp.co.mindshift.ayakashi.api.model.Characters;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class GachaCharacterDTO {
	private Long id;
	private Long gachaId;
	private Long characterId;
	private Double probability;
	private Integer skillLevel;
	private String pickup;
	private String memo;
	private Timestamp createdAt;
	private Timestamp updatedAt;
	private Characters charactersDetail;
}
