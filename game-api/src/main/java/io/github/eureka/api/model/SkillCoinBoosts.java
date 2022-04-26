package io.github.eureka.api.model;

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
 * Date: 26/04/2022<br/>
 */
@Entity
@Table(name = "skill_coin_boosts")
public class SkillCoinBoosts {
    private long id;
    private String name;
    private int level1NumberOfMove;
    private int level1Time;
    private int level1Rate;
    private int level2NumberOfMove;
    private int level2Time;
    private int level2Rate;
    private int level3NumberOfMove;
    private int level3Time;
    private int level3Rate;
    private int level4NumberOfMove;
    private int level4Time;
    private int level4Rate;
    private int level5NumberOfMove;
    private int level5Time;
    private int level5Rate;
    private int level6NumberOfMove;
    private int level6Time;
    private int level6Rate;
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
    @Column(name = "level1_number_of_move")
    public int getLevel1NumberOfMove() {
        return level1NumberOfMove;
    }

    public void setLevel1NumberOfMove(int level1NumberOfMove) {
        this.level1NumberOfMove = level1NumberOfMove;
    }

    @Basic
    @Column(name = "level1_time")
    public int getLevel1Time() {
        return level1Time;
    }

    public void setLevel1Time(int level1Time) {
        this.level1Time = level1Time;
    }

    @Basic
    @Column(name = "level1_rate")
    public int getLevel1Rate() {
        return level1Rate;
    }

    public void setLevel1Rate(int level1Rate) {
        this.level1Rate = level1Rate;
    }

    @Basic
    @Column(name = "level2_number_of_move")
    public int getLevel2NumberOfMove() {
        return level2NumberOfMove;
    }

    public void setLevel2NumberOfMove(int level2NumberOfMove) {
        this.level2NumberOfMove = level2NumberOfMove;
    }

    @Basic
    @Column(name = "level2_time")
    public int getLevel2Time() {
        return level2Time;
    }

    public void setLevel2Time(int level2Time) {
        this.level2Time = level2Time;
    }

    @Basic
    @Column(name = "level2_rate")
    public int getLevel2Rate() {
        return level2Rate;
    }

    public void setLevel2Rate(int level2Rate) {
        this.level2Rate = level2Rate;
    }

    @Basic
    @Column(name = "level3_number_of_move")
    public int getLevel3NumberOfMove() {
        return level3NumberOfMove;
    }

    public void setLevel3NumberOfMove(int level3NumberOfMove) {
        this.level3NumberOfMove = level3NumberOfMove;
    }

    @Basic
    @Column(name = "level3_time")
    public int getLevel3Time() {
        return level3Time;
    }

    public void setLevel3Time(int level3Time) {
        this.level3Time = level3Time;
    }

    @Basic
    @Column(name = "level3_rate")
    public int getLevel3Rate() {
        return level3Rate;
    }

    public void setLevel3Rate(int level3Rate) {
        this.level3Rate = level3Rate;
    }

    @Basic
    @Column(name = "level4_number_of_move")
    public int getLevel4NumberOfMove() {
        return level4NumberOfMove;
    }

    public void setLevel4NumberOfMove(int level4NumberOfMove) {
        this.level4NumberOfMove = level4NumberOfMove;
    }

    @Basic
    @Column(name = "level4_time")
    public int getLevel4Time() {
        return level4Time;
    }

    public void setLevel4Time(int level4Time) {
        this.level4Time = level4Time;
    }

    @Basic
    @Column(name = "level4_rate")
    public int getLevel4Rate() {
        return level4Rate;
    }

    public void setLevel4Rate(int level4Rate) {
        this.level4Rate = level4Rate;
    }

    @Basic
    @Column(name = "level5_number_of_move")
    public int getLevel5NumberOfMove() {
        return level5NumberOfMove;
    }

    public void setLevel5NumberOfMove(int level5NumberOfMove) {
        this.level5NumberOfMove = level5NumberOfMove;
    }

    @Basic
    @Column(name = "level5_time")
    public int getLevel5Time() {
        return level5Time;
    }

    public void setLevel5Time(int level5Time) {
        this.level5Time = level5Time;
    }

    @Basic
    @Column(name = "level5_rate")
    public int getLevel5Rate() {
        return level5Rate;
    }

    public void setLevel5Rate(int level5Rate) {
        this.level5Rate = level5Rate;
    }

    @Basic
    @Column(name = "level6_number_of_move")
    public int getLevel6NumberOfMove() {
        return level6NumberOfMove;
    }

    public void setLevel6NumberOfMove(int level6NumberOfMove) {
        this.level6NumberOfMove = level6NumberOfMove;
    }

    @Basic
    @Column(name = "level6_time")
    public int getLevel6Time() {
        return level6Time;
    }

    public void setLevel6Time(int level6Time) {
        this.level6Time = level6Time;
    }

    @Basic
    @Column(name = "level6_rate")
    public int getLevel6Rate() {
        return level6Rate;
    }

    public void setLevel6Rate(int level6Rate) {
        this.level6Rate = level6Rate;
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
