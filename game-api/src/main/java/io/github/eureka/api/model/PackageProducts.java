package io.github.eureka.api.model;

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
@Table(name = "package_products")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class PackageProducts {
    private long id;
    private long productId;
    private int platformType;
    private long packageId;
    private String packageProductToken;
    private int number;
    private int limit;
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
    @Column(name = "platform_type", nullable = false)
    public int getPlatformType() {
        return platformType;
    }

    public void setPlatformType(int platformType) {
        this.platformType = platformType;
    }

    @Basic
    @Column(name = "package_id", nullable = false)
    public long getPackageId() {
        return packageId;
    }

    public void setPackageId(long packageId) {
        this.packageId = packageId;
    }

    @Basic
    @Column(name = "package_product_token", nullable = false, length = 255)
    public String getPackageProductToken() {
        return packageProductToken;
    }

    public void setPackageProductToken(String packageProductToken) {
        this.packageProductToken = packageProductToken;
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
    @Column(name = "limit", nullable = false)
    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
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
        PackageProducts that = (PackageProducts) o;
        return id == that.id && productId == that.productId && platformType == that.platformType && packageId == that.packageId && number == that.number && limit == that.limit && Objects.equals(packageProductToken, that.packageProductToken) && Objects.equals(thumbnail, that.thumbnail) && Objects.equals(icon, that.icon) && Objects.equals(createdAt, that.createdAt) && Objects.equals(updatedAt, that.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, productId, platformType, packageId, packageProductToken, number, limit, thumbnail, icon, createdAt, updatedAt);
    }
}
