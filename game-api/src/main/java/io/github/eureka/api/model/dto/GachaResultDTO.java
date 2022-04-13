package io.github.eureka.api.model.dto;

import io.github.eureka.api.model.Characters;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
public class GachaResultDTO {
	private Long id;
	private Long gachaId;
	private Long characterId;
	private Double probability;
	private Integer skillLevel;
	private String pickup;
	private String memo;
	private Timestamp createdAt;
	private Timestamp updatedAt;
	private Characters characters;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getGachaId() {
		return gachaId;
	}

	public void setGachaId(Long gachaId) {
		this.gachaId = gachaId;
	}

	public Long getCharacterId() {
		return characterId;
	}

	public void setCharacterId(Long characterId) {
		this.characterId = characterId;
	}

	public Double getProbability() {
		return probability;
	}

	public void setProbability(Double probability) {
		this.probability = probability;
	}

	public Integer getSkillLevel() {
		return skillLevel;
	}

	public void setSkillLevel(Integer skillLevel) {
		this.skillLevel = skillLevel;
	}

	public String getPickup() {
		return pickup;
	}

	public void setPickup(String pickup) {
		this.pickup = pickup;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public Timestamp getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Timestamp updatedAt) {
		this.updatedAt = updatedAt;
	}

	public Characters getCharacters() {
		return characters;
	}

	public void setCharacters(Characters characters) {
		this.characters = characters;
	}
}
