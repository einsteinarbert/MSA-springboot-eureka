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
 * Date: 28/04/2022<br/>
 */
@Entity
@Table(name = "user_quests")
public class UserQuests {
    private Long id;
    private Long userId;
    private Long questId;
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
    @Column(name = "user_id")
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "quest_id")
    public Long getQuestId() {
        return questId;
    }

    public void setQuestId(Long questId) {
        this.questId = questId;
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
        UserQuests userQuests = (UserQuests) o;
        return id == userQuests.id && userId == userQuests.userId && questId == userQuests.questId && Objects.equals(createdAt, userQuests.createdAt) && Objects.equals(updatedAt, userQuests.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, questId, createdAt, updatedAt);
    }
}
