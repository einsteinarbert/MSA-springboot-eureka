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
 * Time: 18:17<br/>
 */
@Entity
public class Administrators {
    private long id;
    private Integer status;
    private String name;
    private String email;
    private String encryptedPassword;
    private String resetPasswordToken;
    private Timestamp resetPasswordSentAt;
    private Timestamp rememberCreatedAt;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private Integer role;

    @Id
    @Column(name = "id", nullable = false)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "status", nullable = true)
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Basic
    @Column(name = "name", nullable = false, length = 255)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "email", nullable = false, length = 255)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "encrypted_password", nullable = false, length = 255)
    public String getEncryptedPassword() {
        return encryptedPassword;
    }

    public void setEncryptedPassword(String encryptedPassword) {
        this.encryptedPassword = encryptedPassword;
    }

    @Basic
    @Column(name = "reset_password_token", nullable = true, length = 255)
    public String getResetPasswordToken() {
        return resetPasswordToken;
    }

    public void setResetPasswordToken(String resetPasswordToken) {
        this.resetPasswordToken = resetPasswordToken;
    }

    @Basic
    @Column(name = "reset_password_sent_at", nullable = true)
    public Timestamp getResetPasswordSentAt() {
        return resetPasswordSentAt;
    }

    public void setResetPasswordSentAt(Timestamp resetPasswordSentAt) {
        this.resetPasswordSentAt = resetPasswordSentAt;
    }

    @Basic
    @Column(name = "remember_created_at", nullable = true)
    public Timestamp getRememberCreatedAt() {
        return rememberCreatedAt;
    }

    public void setRememberCreatedAt(Timestamp rememberCreatedAt) {
        this.rememberCreatedAt = rememberCreatedAt;
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

    @Basic
    @Column(name = "role", nullable = true)
    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Administrators that = (Administrators) o;
        return id == that.id && Objects.equals(status, that.status) && Objects.equals(name, that.name) && Objects.equals(email, that.email) && Objects.equals(encryptedPassword, that.encryptedPassword) && Objects.equals(resetPasswordToken, that.resetPasswordToken) && Objects.equals(resetPasswordSentAt, that.resetPasswordSentAt) && Objects.equals(rememberCreatedAt, that.rememberCreatedAt) && Objects.equals(createdAt, that.createdAt) && Objects.equals(updatedAt, that.updatedAt) && Objects.equals(role, that.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, status, name, email, encryptedPassword, resetPasswordToken, resetPasswordSentAt, rememberCreatedAt, createdAt, updatedAt, role);
    }
}
