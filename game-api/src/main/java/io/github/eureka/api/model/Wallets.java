package io.github.eureka.api.model;

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
public class Wallets {
    private long id;
    private String walletName;
    private int walletType;
    private int jewelType;
    private int limit;
    private Integer platformType;
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
    @Column(name = "wallet_name", nullable = false, length = 255)
    public String getWalletName() {
        return walletName;
    }

    public void setWalletName(String walletName) {
        this.walletName = walletName;
    }

    @Basic
    @Column(name = "wallet_type", nullable = false)
    public int getWalletType() {
        return walletType;
    }

    public void setWalletType(int walletType) {
        this.walletType = walletType;
    }

    @Basic
    @Column(name = "jewel_type", nullable = false)
    public int getJewelType() {
        return jewelType;
    }

    public void setJewelType(int jewelType) {
        this.jewelType = jewelType;
    }

    @Basic
    @Column(name = "limit", nullable = false)
    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    @Basic
    @Column(name = "platform_type", nullable = true)
    public Integer getPlatformType() {
        return platformType;
    }

    public void setPlatformType(Integer platformType) {
        this.platformType = platformType;
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
        return id == wallets.id && walletType == wallets.walletType && jewelType == wallets.jewelType && limit == wallets.limit && Objects.equals(walletName, wallets.walletName) && Objects.equals(platformType, wallets.platformType) && Objects.equals(createdAt, wallets.createdAt) && Objects.equals(updatedAt, wallets.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, walletName, walletType, jewelType, limit, platformType, createdAt, updatedAt);
    }
}
