package jp.co.mindshift.ayakashi.api.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
import java.util.Objects;

/**
 * Project: MSA-springboot-eureka.<br/>
 * Des: <br/>
 * User: HieuTT<br/>
 * Date: 03/05/2022<br/>
 */
@Entity
@Table(name = "login_reward_options")
public class LoginRewardOptions {
    private Long id;
    private Long loginRewardId;
    private Integer day;
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
    @Column(name = "login_reward_id")
    public Long getLoginRewardId() {
        return loginRewardId;
    }

    public void setLoginRewardId(Long loginRewardId) {
        this.loginRewardId = loginRewardId;
    }

    @Basic
    @Column(name = "day")
    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
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
        LoginRewardOptions that = (LoginRewardOptions) o;
        return id == that.id && loginRewardId == that.loginRewardId && day == that.day && Objects.equals(createdAt, that.createdAt) && Objects.equals(updatedAt, that.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, loginRewardId, day, createdAt, updatedAt);
    }
}
