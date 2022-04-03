package io.github.eureka.api.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * Project: MSA-springboot-eureka.<br/>
 * Des: <br/>
 * User: HieuTT<br/>
 * Date: 03/04/2022<br/>
 * Time: 18:17<br/>
 */
@Entity
public class Skins {
    private long id;
    private long itemId;
    private long characterId;
    private String skinToken;
    private int name;
    private String picture;
    private String description;
    private Date startDate;
    private Date endDate;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    @Id
    @Column(name = "id", nullable = false)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "item_id", nullable = false)
    public long getItemId() {
        return itemId;
    }

    public void setItemId(long itemId) {
        this.itemId = itemId;
    }

    @Basic
    @Column(name = "character_id", nullable = false)
    public long getCharacterId() {
        return characterId;
    }

    public void setCharacterId(long characterId) {
        this.characterId = characterId;
    }

    @Basic
    @Column(name = "skin_token", nullable = false, length = 255)
    public String getSkinToken() {
        return skinToken;
    }

    public void setSkinToken(String skinToken) {
        this.skinToken = skinToken;
    }

    @Basic
    @Column(name = "name", nullable = false)
    public int getName() {
        return name;
    }

    public void setName(int name) {
        this.name = name;
    }

    @Basic
    @Column(name = "picture", nullable = false, length = 255)
    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    @Basic
    @Column(name = "description", nullable = false, length = -1)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "start_date", nullable = false)
    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    @Basic
    @Column(name = "end_date", nullable = true)
    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Skins skins = (Skins) o;
        return id == skins.id && itemId == skins.itemId && characterId == skins.characterId && name == skins.name && Objects.equals(skinToken, skins.skinToken) && Objects.equals(picture, skins.picture) && Objects.equals(description, skins.description) && Objects.equals(startDate, skins.startDate) && Objects.equals(endDate, skins.endDate) && Objects.equals(createdAt, skins.createdAt) && Objects.equals(updatedAt, skins.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, itemId, characterId, skinToken, name, picture, description, startDate, endDate, createdAt, updatedAt);
    }
}
