package com.bunbusoft.ayakashi.service.dto;

public class ClientsDTO {
    private Long id;
    private String platformToken;
    private String name;
    private Integer platformType;
    private String platformTypeName;

    public ClientsDTO(Long id, String platformToken, String name, Integer platformType, String platformTypeName) {
        this.id = id;
        this.platformToken = platformToken;
        this.name = name;
        this.platformType = platformType;
        this.platformTypeName = platformTypeName;
    }

    public ClientsDTO() {
    }

    public ClientsDTO(Long id) {
        this.id = id;
    }

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
}
