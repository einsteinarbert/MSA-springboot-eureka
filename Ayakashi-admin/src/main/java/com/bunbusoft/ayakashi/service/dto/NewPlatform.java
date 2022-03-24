package com.bunbusoft.ayakashi.service.dto;

import org.springframework.web.multipart.MultipartFile;

public class NewPlatform {
    private Long id;
    private String platformToken;
    private String name;
    private Integer platformType;
    private String platformTypeName;
    private Integer requiredVersion;
    private String itSecretKey;
    private MultipartFile googlePlayJsonFile;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPlatformToken() {
        return platformToken;
    }

    public void setPlatformToken(String platformToken) {
        this.platformToken = platformToken;
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

    public String getPlatformTypeName() {
        return platformTypeName;
    }

    public void setPlatformTypeName(String platformTypeName) {
        this.platformTypeName = platformTypeName;
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

    public MultipartFile getGooglePlayJsonFile() {
        return googlePlayJsonFile;
    }

    public void setGooglePlayJsonFile(MultipartFile googlePlayJsonFile) {
        this.googlePlayJsonFile = googlePlayJsonFile;
    }
}
