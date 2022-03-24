package com.bunbusoft.ayakashi.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name="platforms")
public class Platforms {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @NotNull
    @Column(name="platform_token")
    private String platFormToken;

    @NotNull
    @Column(name="name")
    private String name;

    @NotNull
    @Column(name="platform_type")
    private Integer platformType;

    @NotNull
    @Column(name="required_version")
    private Integer requiredVersion;

    @Column(columnDefinition = "MEDIUMTEXT", name="it_secret_key")
    private String itSecretKey;

    @NotNull
    @Lob
    @Column(name="google_play_json_file")
    private byte[] googlePlayJsonFile;

    @Column(name="created_at")
    private Date createdAt;

    @Column(name="updated_at")
    private Date updateAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPlatFormToken() {
        return platFormToken;
    }

    public void setPlatFormToken(String platFormToken) {
        this.platFormToken = platFormToken;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPlatformType() {
        return platformType;
    }

    public void setPlatformType(Integer platformType) {
        this.platformType = platformType;
    }

    public Integer getRequiredVersion() {
        return requiredVersion;
    }

    public void setRequiredVersion(Integer requiredVersion) {
        this.requiredVersion = requiredVersion;
    }

    public String getItSecretKey() {
        return itSecretKey;
    }

    public void setItSecretKey(String itSecretKey) {
        this.itSecretKey = itSecretKey;
    }

    public byte[] getGooglePlayJsonFile() {
        return googlePlayJsonFile;
    }

    public void setGooglePlayJsonFile(byte[] googlePlayJsonFile) {
        this.googlePlayJsonFile = googlePlayJsonFile;
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
