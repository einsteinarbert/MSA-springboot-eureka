package io.github.eureka.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.Period;

/**
 * Project: MSA-springboot-eureka.<br/>
 * Des: <br/>
 * User: HieuTT<br/>
 * Date: 03/04/2022<br/>
 * Time: 18:17<br/>
 */
@Entity
@Table(name="users")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Users {
    private Long id;
    private String username;
    private String name;
    private Date birthday;
    private Integer age;
    private String encryptedPassword;
    private String resetPasswordToken;
    private Timestamp resetPasswordSentAt;
    private int status;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private String deviceId;
    private String refreshToken;
    private Long characterId;
    private Long backgroundId;

    @Column(name = "refresh_token")
    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    @Column(name = "character_id")
    public Long getCharacterId() {
        return characterId;
    }

    public void setCharacterId(Long characterId) {
        this.characterId = characterId;
    }

    @Column(name = "background_id")
    public Long getBackgroundId() {
        return backgroundId;
    }

    public void setBackgroundId(Long backgroundId) {
        this.backgroundId = backgroundId;
    }

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "username", nullable = false, length = 255)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
    @Column(name = "birthday", nullable = false)
    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @Basic
    @Column(name = "age", nullable = false, length = 255)
    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
    public void setAge(Date currentDate, Date birthday) {
        LocalDate currDate = currentDate.toLocalDate();
        LocalDate birthDate = birthday.toLocalDate();
        if ((birthDate != null) && (currDate != null)) {
            this.age = Period.between(birthDate, currDate).getYears();
        } else {
            this.age = 0;
        }
    }

    @Basic
    @Column(name = "encrypted_password", nullable = true, length = 255)
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
    @Column(name = "status", nullable = false)
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }


    @Basic
    @Column(name = "device_id", nullable = false)
    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
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
}
