package jp.co.mindshift.ayakashi.api.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
/**
 * Project: MSA-springboot-eureka.<br/>
 * Des: <br/>
 * User: HieuTT<br/>
 * Date: 26/04/2022<br/>
 */
@Entity
@Table(name = "skill_options")
public class SkillOptions {
    private long id;
    private String name;
    private String level1Number;
    private String level1Probability;
    private String level2Number;
    private String level2Probability;
    private String level3Number;
    private String level3Probability;
    private String level4Number;
    private String level4Probability;
    private String level5Number;
    private String level5Probability;
    private String level6Number;
    private String level6Probability;
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
    @Column(name = "level1_number")
    public String getLevel1Number() {
        return level1Number;
    }

    public void setLevel1Number(String level1Number) {
        this.level1Number = level1Number;
    }

    @Basic
    @Column(name = "level1_probability")
    public String getLevel1Probability() {
        return level1Probability;
    }

    public void setLevel1Probability(String level1Probability) {
        this.level1Probability = level1Probability;
    }

    @Basic
    @Column(name = "level2_number")
    public String getLevel2Number() {
        return level2Number;
    }

    public void setLevel2Number(String level2Number) {
        this.level2Number = level2Number;
    }

    @Basic
    @Column(name = "level2_probability")
    public String getLevel2Probability() {
        return level2Probability;
    }

    public void setLevel2Probability(String level2Probability) {
        this.level2Probability = level2Probability;
    }

    @Basic
    @Column(name = "level3_number")
    public String getLevel3Number() {
        return level3Number;
    }

    public void setLevel3Number(String level3Number) {
        this.level3Number = level3Number;
    }

    @Basic
    @Column(name = "level3_probability")
    public String getLevel3Probability() {
        return level3Probability;
    }

    public void setLevel3Probability(String level3Probability) {
        this.level3Probability = level3Probability;
    }

    @Basic
    @Column(name = "level4_number")
    public String getLevel4Number() {
        return level4Number;
    }

    public void setLevel4Number(String level4Number) {
        this.level4Number = level4Number;
    }

    @Basic
    @Column(name = "level4_probability")
    public String getLevel4Probability() {
        return level4Probability;
    }

    public void setLevel4Probability(String level4Probability) {
        this.level4Probability = level4Probability;
    }

    @Basic
    @Column(name = "level5_number")
    public String getLevel5Number() {
        return level5Number;
    }

    public void setLevel5Number(String level5Number) {
        this.level5Number = level5Number;
    }

    @Basic
    @Column(name = "level5_probability")
    public String getLevel5Probability() {
        return level5Probability;
    }

    public void setLevel5Probability(String level5Probability) {
        this.level5Probability = level5Probability;
    }

    @Basic
    @Column(name = "level6_number")
    public String getLevel6Number() {
        return level6Number;
    }

    public void setLevel6Number(String level6Number) {
        this.level6Number = level6Number;
    }

    @Basic
    @Column(name = "level6_probability")
    public String getLevel6Probability() {
        return level6Probability;
    }

    public void setLevel6Probability(String level6Probability) {
        this.level6Probability = level6Probability;
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
