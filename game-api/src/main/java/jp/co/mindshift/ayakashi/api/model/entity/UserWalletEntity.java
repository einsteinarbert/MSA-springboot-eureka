package jp.co.mindshift.ayakashi.api.model.entity;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.util.Date;
import java.util.Objects;

/**
 * Project: MSA-springboot-eureka.<br/>
 * Des: <br/>
 * User: HieuTT<br/>
 * Date: 03/04/2022<br/>
 * Time: 22:43<br/>
 */
@Entity
public class UserWalletEntity {
    @JsonInclude()
    @Transient
    public static final String SQL =
            "select uw.*, w.platform_type, wallet_type, jewel_type, wpm.payment_method_id,\n" +
            "       pm.payment_type,\n" +
            "       pm.name as pay_method\n" +
            "from user_wallets uw,\n" +
            "     wallets w,\n" +
            "     wallet_payment_methods wpm,\n" +
            "     payment_methods pm\n" +
            "where uw.user_id = :user_id\n" +
            "  and w.id = uw.id\n" +
            "  and wpm.id = w.wallet_type\n" +
            "  and pm.id = wpm.payment_method_id\n" +
            "  and pm.payment_type = :pay_type";

    private long id;
    private long walletId;
    private long userId;
    private long number;
    private Date createdAt;
    private Date updatedAt;
    private Integer platformType;
    private int walletType;
    private int jewelType;
    private long paymentMethodId;
    private int paymentType;
    private String payMethod;

    @Id
    @Column(name = "id", nullable = false)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
    @Column(name = "user_id", nullable = false)
    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "number", nullable = false)
    public long getNumber() {
        return number;
    }

    public void setNumber(long number) {
        this.number = number;
    }

    @Basic
    @Column(name = "created_at", nullable = true)
    public  Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    @Basic
    @Column(name = "updated_at", nullable = true)
    public  Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
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
    @Column(name = "wallet_type", nullable = false)
    public int getWalletType() {
        return walletType;
    }

    public void setWalletType(int walletType) {
        this.walletType = walletType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserWalletEntity zzz = (UserWalletEntity) o;
        return id == zzz.id && walletId == zzz.walletId && userId == zzz.userId && number == zzz.number && walletType == zzz.walletType && Objects.equals(createdAt, zzz.createdAt) && Objects.equals(updatedAt, zzz.updatedAt) && Objects.equals(platformType, zzz.platformType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, walletId, userId, number, createdAt, updatedAt, platformType, walletType);
    }

    @Basic
    @Column(name = "payment_method_id", nullable = false)
    public long getPaymentMethodId() {
        return paymentMethodId;
    }

    public void setPaymentMethodId(long paymentMethodId) {
        this.paymentMethodId = paymentMethodId;
    }

    @Basic
    @Column(name = "payment_type", nullable = false)
    public int getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(int paymentType) {
        this.paymentType = paymentType;
    }

    @Basic
    @Column(name = "pay_method", nullable = false, length = 255)
    public String getPayMethod() {
        return payMethod;
    }

    public void setPayMethod(String payMethod) {
        this.payMethod = payMethod;
    }

    @Basic
    @Column(name = "jewel_type")
    public int getJewelType() {
        return jewelType;
    }

    public void setJewelType(int jewelType) {
        this.jewelType = jewelType;
    }
}
