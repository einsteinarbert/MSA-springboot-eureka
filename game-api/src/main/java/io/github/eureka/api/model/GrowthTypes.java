package io.github.eureka.api.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
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
@Table(name = "growth_types")
public class GrowthTypes {
    private long id;
    private String growthTypeToken;
    private String name;
    private int levelMax;
    private int level2;
    private int level3;
    private int level4;
    private int level5;
    private int level6;
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
    @Column(name = "growth_type_token", nullable = false, length = 255)
    public String getGrowthTypeToken() {
        return growthTypeToken;
    }

    public void setGrowthTypeToken(String growthTypeToken) {
        this.growthTypeToken = growthTypeToken;
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
    @Column(name = "level_max", nullable = false)
    public int getLevelMax() {
        return levelMax;
    }

    public void setLevelMax(int levelMax) {
        this.levelMax = levelMax;
    }

    @Basic
    @Column(name = "level2", nullable = false)
    public int getLevel2() {
        return level2;
    }

    public void setLevel2(int level2) {
        this.level2 = level2;
    }

    @Basic
    @Column(name = "level3", nullable = false)
    public int getLevel3() {
        return level3;
    }

    public void setLevel3(int level3) {
        this.level3 = level3;
    }

    @Basic
    @Column(name = "level4", nullable = false)
    public int getLevel4() {
        return level4;
    }

    public void setLevel4(int level4) {
        this.level4 = level4;
    }

    @Basic
    @Column(name = "level5", nullable = false)
    public int getLevel5() {
        return level5;
    }

    public void setLevel5(int level5) {
        this.level5 = level5;
    }

    @Basic
    @Column(name = "level6", nullable = false)
    public int getLevel6() {
        return level6;
    }

    public void setLevel6(int level6) {
        this.level6 = level6;
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
        GrowthTypes that = (GrowthTypes) o;
        return id == that.id && levelMax == that.levelMax && level2 == that.level2 && level3 == that.level3 && level4 == that.level4 && level5 == that.level5 && level6 == that.level6 && Objects.equals(growthTypeToken, that.growthTypeToken) && Objects.equals(name, that.name) && Objects.equals(createdAt, that.createdAt) && Objects.equals(updatedAt, that.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, growthTypeToken, name, levelMax, level2, level3, level4, level5, level6, createdAt, updatedAt);
    }
}
