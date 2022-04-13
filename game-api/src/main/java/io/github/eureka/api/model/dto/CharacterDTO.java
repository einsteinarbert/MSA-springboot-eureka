package io.github.eureka.api.model.dto;

import java.sql.Date;
import java.sql.Timestamp;

public class CharacterDTO {
	private Long id;
	private Long itemId;
	private String characterToken;
	private String name;
	private Long skillId;
	private Long growthTypeId;
	private String situation;
	private Integer genderType;
	private Integer getRoute;
	private String stand;
	private String thumbnail;
	private String icon;
	private String skillGage;
	private String cutin;
	private Date startDate;
	private Date endDate;
	private Timestamp createdAt;
	private Timestamp updatedAt;

	public CharacterDTO(Long id, Long itemId, String characterToken, String name, Long skillId, Long growthTypeId, String situation, Integer genderType, Integer getRoute, String stand, String thumbnail, String icon, String skillGage, String cutin, Date startDate, Date endDate, Timestamp createdAt, Timestamp updatedAt) {
		this.id = id;
		this.itemId = itemId;
		this.characterToken = characterToken;
		this.name = name;
		this.skillId = skillId;
		this.growthTypeId = growthTypeId;
		this.situation = situation;
		this.genderType = genderType;
		this.getRoute = getRoute;
		this.stand = stand;
		this.thumbnail = thumbnail;
		this.icon = icon;
		this.skillGage = skillGage;
		this.cutin = cutin;
		this.startDate = startDate;
		this.endDate = endDate;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	public CharacterDTO() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getItemId() {
		return itemId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

	public String getCharacterToken() {
		return characterToken;
	}

	public void setCharacterToken(String characterToken) {
		this.characterToken = characterToken;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getSkillId() {
		return skillId;
	}

	public void setSkillId(Long skillId) {
		this.skillId = skillId;
	}

	public Long getGrowthTypeId() {
		return growthTypeId;
	}

	public void setGrowthTypeId(Long growthTypeId) {
		this.growthTypeId = growthTypeId;
	}

	public String getSituation() {
		return situation;
	}

	public void setSituation(String situation) {
		this.situation = situation;
	}

	public Integer getGenderType() {
		return genderType;
	}

	public void setGenderType(Integer genderType) {
		this.genderType = genderType;
	}

	public Integer getGetRoute() {
		return getRoute;
	}

	public void setGetRoute(Integer getRoute) {
		this.getRoute = getRoute;
	}

	public String getStand() {
		return stand;
	}

	public void setStand(String stand) {
		this.stand = stand;
	}

	public String getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getSkillGage() {
		return skillGage;
	}

	public void setSkillGage(String skillGage) {
		this.skillGage = skillGage;
	}

	public String getCutin() {
		return cutin;
	}

	public void setCutin(String cutin) {
		this.cutin = cutin;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
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
}
