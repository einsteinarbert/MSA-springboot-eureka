package com.bunbusoft.ayakashi.service.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ClientssDTO {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "platform_token", nullable = false)
    private String platformToken;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "platform_type", nullable = false)
    private Integer platformType;

    @Column(name = "platform_type_name", nullable = false, length = 7)
    private String platformTypeName;

    @Column(name = "required_version", nullable = false)
    private Integer requiredVersion;

    public Integer getRequiredVersion() {
        return requiredVersion;
    }

    public String getPlatformTypeName() {
        return platformTypeName;
    }

    public Integer getPlatformType() {
        return platformType;
    }

    public String getName() {
        return name;
    }

    public String getPlatformToken() {
        return platformToken;
    }

    public Long getId() {
        return id;
    }

    public ClientssDTO() {
    }
}