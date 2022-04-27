package jp.co.mindshift.ayakashi.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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
@Table(name = "jewel_products")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class JewelProducts {
    private long id;
    private long productId;
    private String jewelProductToken;
    private int number;
    private long walletId;
    private int bonusNumber;
    private long bonusWalletId;
    private String thumbnail;
    private String icon;
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
    @Column(name = "product_id", nullable = false)
    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    @Basic
    @Column(name = "jewel_product_token", nullable = false, length = 255)
    public String getJewelProductToken() {
        return jewelProductToken;
    }

    public void setJewelProductToken(String jewelProductToken) {
        this.jewelProductToken = jewelProductToken;
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
    @Column(name = "bonus_number", nullable = false)
    public int getBonusNumber() {
        return bonusNumber;
    }

    public void setBonusNumber(int bonusNumber) {
        this.bonusNumber = bonusNumber;
    }

    @Basic
    @Column(name = "bonus_wallet_id", nullable = false)
    public long getBonusWalletId() {
        return bonusWalletId;
    }

    public void setBonusWalletId(long bonusWalletId) {
        this.bonusWalletId = bonusWalletId;
    }

    @Basic
    @Column(name = "thumbnail", nullable = false, length = 255)
    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    @Basic
    @Column(name = "icon", nullable = false, length = 255)
    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
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
    @Column(name = "updated_at", nullable = false)
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
        return id == that.id && productId == that.productId && number == that.number && walletId == that.walletId && bonusNumber == that.bonusNumber && bonusWalletId == that.bonusWalletId && Objects.equals(jewelProductToken, that.jewelProductToken) && Objects.equals(thumbnail, that.thumbnail) && Objects.equals(icon, that.icon) && Objects.equals(createdAt, that.createdAt) && Objects.equals(updatedAt, that.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, productId, jewelProductToken, number, walletId, bonusNumber, bonusWalletId, thumbnail, icon, createdAt, updatedAt);
    }
}
