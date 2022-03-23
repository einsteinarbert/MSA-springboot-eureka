package com.bunbusoft.ayakashi.service.dto;

public class ClientsDTO {
    private Long id;
    private String clientToken;
    private String name;
    private Integer clientType;
    private String clientTypeName;

    public ClientsDTO(Long id, String clientToken, String name, Integer clientType, String clientTypeName) {
        this.id = id;
        this.clientToken = clientToken;
        this.name = name;
        this.clientType = clientType;
        this.clientTypeName = clientTypeName;
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
}
