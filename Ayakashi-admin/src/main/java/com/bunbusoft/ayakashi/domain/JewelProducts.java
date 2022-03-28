package com.bunbusoft.ayakashi.domain;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "jewel_products")
public class JewelProducts {
    private long id;
    private String token;
    private long clientId;
    private int number;
    private long walletId;
    private Integer bonusNumber;
    private Byte bonusPercentage;
    private Long bonusWalletId;
    private int status;
    private Date startDate;
    private Date endDate;
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
    @Column(name = "token", nullable = false, length = 255)
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Basic
    @Column(name = "client_id", nullable = false)
    public long getClientId() {
        return clientId;
    }

    public void setClientId(long clientId) {
        this.clientId = clientId;
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
    @Column(name = "wallet_id", nullable = false)
    public long getWalletId() {
        return walletId;
    }

    public void setWalletId(long walletId) {
        this.walletId = walletId;
    }

    @Basic
    @Column(name = "bonus_number", nullable = true)
    public Integer getBonusNumber() {
        return bonusNumber;
    }

    public void setBonusNumber(Integer bonusNumber) {
        this.bonusNumber = bonusNumber;
    }

    @Basic
    @Column(name = "bonus_percentage", nullable = true)
    public Byte getBonusPercentage() {
        return bonusPercentage;
    }

    public void setBonusPercentage(Byte bonusPercentage) {
        this.bonusPercentage = bonusPercentage;
    }

    @Basic
    @Column(name = "bonus_wallet_id", nullable = true)
    public Long getBonusWalletId() {
        return bonusWalletId;
    }

    public void setBonusWalletId(Long bonusWalletId) {
        this.bonusWalletId = bonusWalletId;
    }

    @Basic
    @Column(name = "status", nullable = false)
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
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
        JewelProducts that = (JewelProducts) o;
        return id == that.id && clientId == that.clientId && number == that.number && walletId == that.walletId && status == that.status && Objects.equals(token, that.token) && Objects.equals(bonusNumber, that.bonusNumber) && Objects.equals(bonusPercentage, that.bonusPercentage) && Objects.equals(bonusWalletId, that.bonusWalletId) && Objects.equals(startDate, that.startDate) && Objects.equals(endDate, that.endDate) && Objects.equals(createdAt, that.createdAt) && Objects.equals(updatedAt, that.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, token, clientId, number, walletId, bonusNumber, bonusPercentage, bonusWalletId, status, startDate, endDate, createdAt, updatedAt);
    }
}
