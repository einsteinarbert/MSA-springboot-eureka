package io.github.eureka.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * Project: MSA-springboot-eureka.<br/>
 * Des: <br/>
 * User: HieuTT<br/>
 * Date: 03/04/2022<br/>
 * Time: 18:18<br/>
 */
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Wallets {
    private Long id;
    private String walletName;
    private Integer walletType;
    private Integer jewelType;
    private Integer limitNumber;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    @Id
    @Column(name = "id", nullable = false)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "wallet_name", nullable = false, length = 255)
    public String getWalletName() {
        return walletName;
    }

    public void setWalletName(String walletName) {
        this.walletName = walletName;
    }

    @Basic
    @Column(name = "wallet_type", nullable = false)
    public Integer getWalletType() {
        return walletType;
    }

    public void setWalletType(Integer walletType) {
        this.walletType = walletType;
    }


    @Basic
    @Column(name = "jewel_type", nullable = false)
    public Integer getJewelType() {
        return jewelType;
    }

    public void setJewelType(Integer jewelType) {
        this.jewelType = jewelType;
    }

    @Basic
    @Column(name = "limit_number", nullable = false)
    public Integer getLimitNumber() {
        return limitNumber;
    }

    public void setLimitNumber(Integer limitNumber) {
        this.limitNumber = limitNumber;
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
        Wallets wallets = (Wallets) o;
        return id == wallets.id && walletType == wallets.walletType && limitNumber == wallets.limitNumber && Objects.equals(walletName, wallets.walletName) && Objects.equals(createdAt, wallets.createdAt) && Objects.equals(updatedAt, wallets.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, walletName, walletType, limitNumber, createdAt, updatedAt);
    }
}
