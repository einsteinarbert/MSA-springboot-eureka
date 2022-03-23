package com.bunbusoft.ayakashi.service.dto;

import org.springframework.web.multipart.MultipartFile;

public class NewClientForm {
    private Long id;
    private String clientToken;
    private String name;
    private Integer clientType;
    private String clientTypeName;
    private Integer requiredVersion;
    private String itSecretKey;
    private MultipartFile googlePlayJsonFile;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClientToken() {
        return clientToken;
    }

    public void setClientToken(String clientToken) {
        this.clientToken = clientToken;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getClientType() {
        return clientType;
    }

    public void setClientType(Integer clientType) {
        this.clientType = clientType;
    }

    public String getClientTypeName() {
        return clientTypeName;
    }

    public void setClientTypeName(String clientTypeName) {
        this.clientTypeName = clientTypeName;
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
