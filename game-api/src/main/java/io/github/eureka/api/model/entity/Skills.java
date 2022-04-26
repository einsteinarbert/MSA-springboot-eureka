package io.github.eureka.api.model.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

/**
 * Project: MSA-springboot-eureka.<br/>
 * Des: <br/>
 * User: HieuTT<br/>
 * Date: 26/04/2022<br/>
 */
@Entity
public class Skills {
    private long id;
    private String name;
    private int skillType;
    private Long skillOptionId;
    private Integer changeType;
    private Integer patternType;
    private Long skillCoinBoostId;
    private Date createdAt;
    private Date updatedAt;

    @Id
    @Column(name = "id")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "skill_type")
    public int getSkillType() {
        return skillType;
    }

    public void setSkillType(int skillType) {
        this.skillType = skillType;
    }

    @Basic
    @Column(name = "skill_option_id")
    public Long getSkillOptionId() {
        return skillOptionId;
    }

    public void setSkillOptionId(Long skillOptionId) {
        this.skillOptionId = skillOptionId;
    }

    @Basic
    @Column(name = "change_type")
    public Integer getChangeType() {
        return changeType;
    }

    public void setChangeType(Integer changeType) {
        this.changeType = changeType;
    }

    @Basic
    @Column(name = "pattern_type")
    public Integer getPatternType() {
        return patternType;
    }

    public void setPatternType(Integer patternType) {
        this.patternType = patternType;
    }

    @Basic
    @Column(name = "skill_coin_boost_id")
    public Long getSkillCoinBoostId() {
        return skillCoinBoostId;
    }

    public void setSkillCoinBoostId(Long skillCoinBoostId) {
        this.skillCoinBoostId = skillCoinBoostId;
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
}
