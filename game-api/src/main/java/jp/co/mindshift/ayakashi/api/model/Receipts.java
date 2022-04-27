package jp.co.mindshift.ayakashi.api.model;

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
 * Time: 18:17<br/>
 */
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Receipts {
    private long id;
    private int platformType;
    private long userId;
    private String transactionIdToken;
    private String receipt;
    private Integer result;
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
    @Column(name = "platform_type", nullable = false)
    public int getPlatformType() {
        return platformType;
    }

    public void setPlatformType(int platformType) {
        this.platformType = platformType;
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
    @Column(name = "transaction_id_token", nullable = false, length = 255)
    public String getTransactionIdToken() {
        return transactionIdToken;
    }

    public void setTransactionIdToken(String transactionIdToken) {
        this.transactionIdToken = transactionIdToken;
    }

    @Basic
    @Column(name = "receipt", nullable = false, length = -1)
    public String getReceipt() {
        return receipt;
    }

    public void setReceipt(String receipt) {
        this.receipt = receipt;
    }

    @Basic
    @Column(name = "result", nullable = true)
    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
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
        Receipts receipts = (Receipts) o;
        return id == receipts.id && platformType == receipts.platformType && userId == receipts.userId && Objects.equals(transactionIdToken, receipts.transactionIdToken) && Objects.equals(receipt, receipts.receipt) && Objects.equals(result, receipts.result) && Objects.equals(createdAt, receipts.createdAt) && Objects.equals(updatedAt, receipts.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, platformType, userId, transactionIdToken, receipt, result, createdAt, updatedAt);
    }
}
