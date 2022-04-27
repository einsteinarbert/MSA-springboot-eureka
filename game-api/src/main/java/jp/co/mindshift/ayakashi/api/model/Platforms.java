package jp.co.mindshift.ayakashi.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;
import java.util.Arrays;
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
public class Platforms {
    private long id;
    private String platformToken;
    private String name;
    private int platformType;
    private int requiredVersion;
    private String itSecretKey;
    private byte[] googlePlayJsonFile;
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
    @Column(name = "platform_token", nullable = false, length = 255)
    public String getPlatformToken() {
        return platformToken;
    }

    public void setPlatformToken(String platformToken) {
        this.platformToken = platformToken;
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
    @Column(name = "platform_type", nullable = false)
    public int getPlatformType() {
        return platformType;
    }

    public void setPlatformType(int platformType) {
        this.platformType = platformType;
    }

    @Basic
    @Column(name = "required_version", nullable = false)
    public int getRequiredVersion() {
        return requiredVersion;
    }

    public void setRequiredVersion(int requiredVersion) {
        this.requiredVersion = requiredVersion;
    }

    @Basic
    @Column(name = "it_secret_key", nullable = true, length = -1)
    public String getItSecretKey() {
        return itSecretKey;
    }

    public void setItSecretKey(String itSecretKey) {
        this.itSecretKey = itSecretKey;
    }

    @Basic
    @Column(name = "google_play_json_file", nullable = false)
    public byte[] getGooglePlayJsonFile() {
        return googlePlayJsonFile;
    }

    public void setGooglePlayJsonFile(byte[] googlePlayJsonFile) {
        this.googlePlayJsonFile = googlePlayJsonFile;
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
        Platforms platforms = (Platforms) o;
        return id == platforms.id && platformType == platforms.platformType && requiredVersion == platforms.requiredVersion && Objects.equals(platformToken, platforms.platformToken) && Objects.equals(name, platforms.name) && Objects.equals(itSecretKey, platforms.itSecretKey) && Arrays.equals(googlePlayJsonFile, platforms.googlePlayJsonFile) && Objects.equals(createdAt, platforms.createdAt) && Objects.equals(updatedAt, platforms.updatedAt);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(id, platformToken, name, platformType, requiredVersion, itSecretKey, createdAt, updatedAt);
        result = 31 * result + Arrays.hashCode(googlePlayJsonFile);
        return result;
    }
}
