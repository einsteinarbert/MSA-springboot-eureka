package com.bunbusoft.ayakashi.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name="administrators")
public class Administrators {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @NotNull
    @Column(name = "activation")
    private Integer activation;

    @Column(name="name")
    private String name;

    @Column(name = "email")
    private String email;

    @NotNull
    @Column(name = "encrypted_password")
    private String encryptedPassword;

    @Column(name="reset_password_token")
    private String resetPasswordToken;

    @Column(name="reset_password_sent_at")
    private Date resetPasswordSentAt;
    @Lob
    @Column(name = "remember_created_at")
    private Date rememberCreatedAt;

    @Column(name="created_at")
    private Date createdAt;

    @Column(name="updated_at")
    private Date updateAt;

    public Administrators() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getActivation() {
        return activation;
    }

    public void setActivation(Integer activation) {
        this.activation = activation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEncryptedPassword() {
        return encryptedPassword;
    }

    public void setEncryptedPassword(String encryptedPassword) {
        this.encryptedPassword = encryptedPassword;
    }

    public String getResetPasswordToken() {
        return resetPasswordToken;
    }

    public void setResetPasswordToken(String resetPasswordToken) {
        this.resetPasswordToken = resetPasswordToken;
    }

    public Date getResetPasswordSentAt() {
        return resetPasswordSentAt;
    }

    public void setResetPasswordSentAt(Date resetPasswordSentAt) {
        this.resetPasswordSentAt = resetPasswordSentAt;
    }

    public Date getRememberCreatedAt() {
        return rememberCreatedAt;
    }

    public void setRememberCreatedAt(Date rememberCreatedAt) {
        this.rememberCreatedAt = rememberCreatedAt;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }
}
