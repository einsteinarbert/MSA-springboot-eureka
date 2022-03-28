package com.bunbusoft.ayakashi.domain.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class JewelResultEntity {
    private long id;

    private long clientId;

    private String productCode;

    private long walletId;

    private String walletName;

    private int number;

    private Long bonusWalletId;

    private String bonusWalletName;

    private Integer bonusNumber;

    private String isBought;

    @Id
    @Basic
    @Column(name = "id", nullable = false)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
    @Column(name = "wallet_id", nullable = false)
    public long getWalletId() {
        return walletId;
    }

    public void setWalletId(long walletId) {
        this.walletId = walletId;
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
    @Column(name = "number", nullable = false)
    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
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
    @Column(name = "bonus_wallet_name", nullable = false, length = 255)
    public String getBonusWalletName() {
        return bonusWalletName;
    }

    public void setBonusWalletName(String bonusWalletName) {
        this.bonusWalletName = bonusWalletName;
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
    @Column(name = "is_bought", nullable = false, length = 5)
    public String getIsBought() {
        return isBought;
    }

    public void setIsBought(String isBought) {
        this.isBought = isBought;
    }

    @Column(name = "product_code")
    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }
}
