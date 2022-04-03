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
public class Characters {
    private long id;
    private long itemId;
    private String characterToken;
    private int name;
    private long skillId;
    private long growthTypeId;
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
    @Column(name = "character_token", nullable = false, length = 255)
    public String getCharacterToken() {
        return characterToken;
    }

    public void setCharacterToken(String characterToken) {
        this.characterToken = characterToken;
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
    @Column(name = "skill_id", nullable = false)
    public long getSkillId() {
        return skillId;
    }

    public void setSkillId(long skillId) {
        this.skillId = skillId;
    }

    @Basic
    @Column(name = "growth_type_id", nullable = false)
    public long getGrowthTypeId() {
        return growthTypeId;
    }

    public void setGrowthTypeId(long growthTypeId) {
        this.growthTypeId = growthTypeId;
    }

    @Basic
    @Column(name = "situation", nullable = true, length = 3)
    public String getSituation() {
        return situation;
    }

    public void setSituation(String situation) {
        this.situation = situation;
    }

    @Basic
    @Column(name = "gender_type", nullable = true)
    public Integer getGenderType() {
        return genderType;
    }

    public void setGenderType(Integer genderType) {
        this.genderType = genderType;
    }

    @Basic
    @Column(name = "get_route", nullable = true)
    public Integer getGetRoute() {
        return getRoute;
    }

    public void setGetRoute(Integer getRoute) {
        this.getRoute = getRoute;
    }

    @Basic
    @Column(name = "stand", nullable = true, length = 255)
    public String getStand() {
        return stand;
    }

    public void setStand(String stand) {
        this.stand = stand;
    }

    @Basic
    @Column(name = "thumbnail", nullable = true, length = 255)
    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    @Basic
    @Column(name = "icon", nullable = true, length = 255)
    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    @Basic
    @Column(name = "skill_gage", nullable = true, length = 255)
    public String getSkillGage() {
        return skillGage;
    }

    public void setSkillGage(String skillGage) {
        this.skillGage = skillGage;
    }

    @Basic
    @Column(name = "cutin", nullable = true, length = 255)
    public String getCutin() {
        return cutin;
    }

    public void setCutin(String cutin) {
        this.cutin = cutin;
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
        Characters that = (Characters) o;
        return id == that.id && itemId == that.itemId && name == that.name && skillId == that.skillId && growthTypeId == that.growthTypeId && Objects.equals(characterToken, that.characterToken) && Objects.equals(situation, that.situation) && Objects.equals(genderType, that.genderType) && Objects.equals(getRoute, that.getRoute) && Objects.equals(stand, that.stand) && Objects.equals(thumbnail, that.thumbnail) && Objects.equals(icon, that.icon) && Objects.equals(skillGage, that.skillGage) && Objects.equals(cutin, that.cutin) && Objects.equals(startDate, that.startDate) && Objects.equals(endDate, that.endDate) && Objects.equals(createdAt, that.createdAt) && Objects.equals(updatedAt, that.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, itemId, characterToken, name, skillId, growthTypeId, situation, genderType, getRoute, stand, thumbnail, icon, skillGage, cutin, startDate, endDate, createdAt, updatedAt);
    }
}
