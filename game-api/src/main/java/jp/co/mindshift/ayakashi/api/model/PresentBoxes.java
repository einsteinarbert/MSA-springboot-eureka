package jp.co.mindshift.ayakashi.api.model;

import javax.persistence.*;
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
@Table(name = "present_boxes")
public class PresentBoxes {
    private Long id;
    private Long userId;
    private Date expiredAt;
    private Integer status;
    private String generatableType;
    private Long generatableId;
    private Date createdAt;
    private Date updatedAt;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "user_id")
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "expired_at")
    public Date getExpiredAt() {
        return expiredAt;
    }

    public void setExpiredAt(Date expiredAt) {
        this.expiredAt = expiredAt;
    }

    @Basic
    @Column(name = "status")
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Basic
    @Column(name = "generatable_type")
    public String getGeneratableType() {
        return generatableType;
    }

    public void setGeneratableType(String generatableType) {
        this.generatableType = generatableType;
    }

    @Basic
    @Column(name = "generatable_id")
    public Long getGeneratableId() {
        return generatableId;
    }

    public void setGeneratableId(Long generatableId) {
        this.generatableId = generatableId;
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
        PresentBoxes that = (PresentBoxes) o;
        return id == that.id && userId == that.userId  && status == that.status && Objects.equals(expiredAt, that.expiredAt) && Objects.equals(generatableType, that.generatableType) && Objects.equals(generatableId, that.generatableId) && Objects.equals(createdAt, that.createdAt) && Objects.equals(updatedAt, that.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, expiredAt, status, generatableType, generatableId, createdAt, updatedAt);
    }
}
