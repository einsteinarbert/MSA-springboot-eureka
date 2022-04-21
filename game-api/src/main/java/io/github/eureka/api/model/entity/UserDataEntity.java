package io.github.eureka.api.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.github.eureka.api.model.Background;
import io.github.eureka.api.model.Characters;

import javax.persistence.*;

/**
 * Project: MSA-springboot-eureka.<br/>
 * Des: <br/>
 * User: HieuTT<br/>
 * Date: 15/04/2022<br/>
 */
@Entity
public class UserDataEntity {
	private Long id;
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
	private Long stage;
	
	@Id
	@Column(name = "id")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	@Basic
	@Column(name = "username")
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Basic
	@Column(name = "name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Basic
	@Column(name = "age")
	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	@Basic
	@Column(name = "character_id")
	public Long getCharacterId() {
		return characterId;
	}

	public void setCharacterId(Long characterId) {
		this.characterId = characterId;
	}


	@Basic
	@Column(name = "stage")
	public Long getStage() {
		return stage;
	}

	public void setStage(Long stage) {
		this.stage = stage;
	}

	@Basic
	@Column(name = "background_id")
	public Long getBackgroundId() {
		return backgroundId;
	}

	public void setBackgroundId(Long backgroundId) {
		this.backgroundId = backgroundId;
	}

	@Basic
	@Column(name = "jewel_number")
	public Long getJewelNumber() {
		return jewelNumber;
	}

	public void setJewelNumber(Long jewelNumber) {
		this.jewelNumber = jewelNumber;
	}

	@Basic
	@Column(name = "jewel_bonus_number")
	public Long getJewelBonusNumber() {
		return jewelBonusNumber;
	}

	public void setJewelBonusNumber(Long jewelBonusNumber) {
		this.jewelBonusNumber = jewelBonusNumber;
	}

	@Basic
	@Column(name = "coin_number")
	public Long getCoinNumber() {
		return coinNumber;
	}

	public void setCoinNumber(Long coinNumber) {
		this.coinNumber = coinNumber;
	}

	@Basic
	@Column(name = "stamina_number")
	public Long getStaminaNumber() {
		return staminaNumber;
	}

	public void setStaminaNumber(Long staminaNumber) {
		this.staminaNumber = staminaNumber;
	}

	@Basic
	@Column(name = "heart")
	public Long getHeart() {
		return heart;
	}

	public void setHeart(Long heart) {
		this.heart = heart;
	}

	@Basic
	@Column(name = "heart_30")
	public Long getHeart30() {
		return heart30;
	}

	public void setHeart30(Long heart30) {
		this.heart30 = heart30;
	}

	@Basic
	@Column(name = "heart_60")
	public Long getHeart60() {
		return heart60;
	}

	public void setHeart60(Long heart60) {
		this.heart60 = heart60;
	}

}
