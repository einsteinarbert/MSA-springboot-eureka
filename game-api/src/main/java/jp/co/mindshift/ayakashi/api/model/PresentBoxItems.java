package jp.co.mindshift.ayakashi.api.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Objects;

/**
 * Project: MSA-springboot-eureka.<br/>
 * Des: <br/>
 * User: HieuTT<br/>
 * Date: 03/05/2022<br/>
 */
@Entity
@Table(name = "present_box_items")
public class PresentBoxItems {
    private Long id;
    private Long presentBoxId;
    private Long itemId;
    private Integer itemNumber;
    private Date createdAt;
    private Date updatedAt;

    @Id
    @Column(name = "id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "present_box_id")
    public Long getPresentBoxId() {
        return presentBoxId;
    }

    public void setPresentBoxId(Long presentBoxId) {
        this.presentBoxId = presentBoxId;
    }

    @Basic
    @Column(name = "item_id")
    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    @Basic
    @Column(name = "item_number")
    public Integer getItemNumber() {
        return itemNumber;
    }

    public void setItemNumber(Integer itemNumber) {
        this.itemNumber = itemNumber;
    }

    @Basic
    @Column(name = "created_at")
    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    @Basic
    @Column(name = "updated_at")
    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PresentBoxItems that = (PresentBoxItems) o;
        return id == that.id && presentBoxId == that.presentBoxId && itemId == that.itemId && Objects.equals(itemNumber, that.itemNumber) && Objects.equals(createdAt, that.createdAt) && Objects.equals(updatedAt, that.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, presentBoxId, itemId, itemNumber, createdAt, updatedAt);
    }
}
