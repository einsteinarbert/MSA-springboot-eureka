package com.bunbusoft.ayakashi.service.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ClientssDTO {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "client_token", nullable = false)
    private String clientToken;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "client_type", nullable = false)
    private Integer clientType;

    @Column(name = "client_type_name", nullable = false, length = 7)
    private String clientTypeName;

    @Column(name = "required_version", nullable = false)
    private Integer requiredVersion;

    public Integer getRequiredVersion() {
        return requiredVersion;
    }

    public String getClientTypeName() {
        return clientTypeName;
    }

    public Integer getClientType() {
        return clientType;
    }

    public String getName() {
        return name;
    }

    public String getClientToken() {
        return clientToken;
    }

    public Long getId() {
        return id;
    }

    public ClientssDTO() {
    }
}