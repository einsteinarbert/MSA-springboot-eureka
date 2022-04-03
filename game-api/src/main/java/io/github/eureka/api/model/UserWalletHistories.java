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
@Table(name = "user_wallet_histories")
public class UserWalletHistories {
    private long id;
    private long userId;
    private long walletId;
    private int supplyNumber;
    private int number;
    private String message;
    private Integer historyType;
    private String generatableType;
    private Long generatableId;
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
    @Column(name = "user_id", nullable = false)
    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "wallet_id", nullable = false)
    public long getWalletId() {
        return walletId;
    }

    public void setWalletId(long walletId) {
        this.walletId = walletId;
    }

    @Basic
    @Column(name = "supply_number", nullable = false)
    public int getSupplyNumber() {
        return supplyNumber;
    }

    public void setSupplyNumber(int supplyNumber) {
        this.supplyNumber = supplyNumber;
    }

    @Basic
    @Column(name = "number", nullable = false)
    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @Basic
    @Column(name = "message", nullable = false, length = 255)
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Basic
    @Column(name = "history_type", nullable = true)
    public Integer getHistoryType() {
        return historyType;
    }

    public void setHistoryType(Integer historyType) {
        this.historyType = historyType;
    }

    @Basic
    @Column(name = "generatable_type", nullable = true, length = 255)
    public String getGeneratableType() {
        return generatableType;
    }

    public void setGeneratableType(String generatableType) {
        this.generatableType = generatableType;
    }

    @Basic
    @Column(name = "generatable_id", nullable = true)
    public Long getGeneratableId() {
        return generatableId;
    }

    public void setGeneratableId(Long generatableId) {
        this.generatableId = generatableId;
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
        UserWalletHistories that = (UserWalletHistories) o;
        return id == that.id && userId == that.userId && walletId == that.walletId && supplyNumber == that.supplyNumber && number == that.number && Objects.equals(message, that.message) && Objects.equals(historyType, that.historyType) && Objects.equals(generatableType, that.generatableType) && Objects.equals(generatableId, that.generatableId) && Objects.equals(createdAt, that.createdAt) && Objects.equals(updatedAt, that.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, walletId, supplyNumber, number, message, historyType, generatableType, generatableId, createdAt, updatedAt);
    }
}
