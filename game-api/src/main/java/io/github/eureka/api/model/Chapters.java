package io.github.eureka.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;

@Entity
@Table(name="chapters")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Chapters {
	private Long id;
	private String name;
	private Integer position;
	private String thumbnail;
	private String icon;
	private Timestamp createdAt;
	private Timestamp updatedAt;

	@Id
	@Column(name = "id", nullable = false)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Basic
	@Column(name = "name", nullable = false, length = 255)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Basic
	@Column(name = "position", nullable = false)
	public Integer getPosition() {
		return position;
	}

	public void setPosition(Integer position) {
		this.position = position;
	}
	
	@Basic
	@Column(name = "thumbnail", nullable = false, length = 255)
	public String getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}

	@Basic
	@Column(name = "icon", nullable = false, length = -1)
	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}


	@Basic
	@Column(name = "created_at", nullable = true)
	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	@Basic
	@Column(name = "updated_at", nullable = true)
	public Timestamp getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Timestamp updatedAt) {
		this.updatedAt = updatedAt;
	}
}
