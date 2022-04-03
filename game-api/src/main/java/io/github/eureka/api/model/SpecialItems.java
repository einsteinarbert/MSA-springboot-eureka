package io.github.eureka.api.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
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
@Table(name = "special_items")
public class SpecialItems {
    private long id;
    private long itemId;
    private String specialItemToken;
    private String name;
    private int specialItemType;
    private String picture;
    private String description;
    private Date startDate;
    private Date endDate;
    private Timestamp createdDate;
    private Timestamp updatedDate;

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
    @Column(name = "special_item_token", nullable = false, length = 255)
    public String getSpecialItemToken() {
        return specialItemToken;
    }

    public void setSpecialItemToken(String specialItemToken) {
        this.specialItemToken = specialItemToken;
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
    @Column(name = "special_item_type", nullable = false)
    public int getSpecialItemType() {
        return specialItemType;
    }

    public void setSpecialItemType(int specialItemType) {
        this.specialItemType = specialItemType;
    }

    @Basic
    @Column(name = "picture", nullable = false, length = -1)
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
    @Column(name = "created_date", nullable = true)
    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    @Basic
    @Column(name = "updated_date", nullable = true)
    public Timestamp getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Timestamp updatedDate) {
        this.updatedDate = updatedDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SpecialItems that = (SpecialItems) o;
        return id == that.id && itemId == that.itemId && specialItemType == that.specialItemType && Objects.equals(specialItemToken, that.specialItemToken) && Objects.equals(name, that.name) && Objects.equals(picture, that.picture) && Objects.equals(description, that.description) && Objects.equals(startDate, that.startDate) && Objects.equals(endDate, that.endDate) && Objects.equals(createdDate, that.createdDate) && Objects.equals(updatedDate, that.updatedDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, itemId, specialItemToken, name, specialItemType, picture, description, startDate, endDate, createdDate, updatedDate);
    }
}
