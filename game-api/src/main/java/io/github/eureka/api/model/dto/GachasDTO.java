package io.github.eureka.api.model.dto;

import java.sql.Date;
import java.sql.Timestamp;

public class GachasDTO {
	private Long id;
	private Long itemId;
	private String name;
	private Integer gachaTicketType;
	private String thumbnail;
	private String icon;
	private Date startDate;
	private Date endDate;
	private Timestamp createdAt;
	private Timestamp updatedAt;

	public GachasDTO(Long id, Long itemId, String name, Integer gachaTicketType, String thumbnail, String icon, Date startDate, Date endDate, Timestamp createdAt, Timestamp updatedAt) {
		this.id = id;
		this.itemId = itemId;
		this.name = name;
		this.gachaTicketType = gachaTicketType;
		this.thumbnail = thumbnail;
		this.icon = icon;
		this.startDate = startDate;
		this.endDate = endDate;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	public GachasDTO() {
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getGachaTicketType() {
		return gachaTicketType;
	}

	public void setGachaTicketType(Integer gachaTicketType) {
		this.gachaTicketType = gachaTicketType;
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
