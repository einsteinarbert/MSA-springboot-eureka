package io.github.eureka.api.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * Project: MSA-springboot-eureka.<br/>
 * Des: <br/>
 * User: HieuTT<br/>
 * Date: 03/04/2022<br/>
 * Time: 18:17<br/>
 */
@Entity
public class Scenarios {
    private long id;
    private String scenarioToken;
    private String name;
    private Integer releaseType;
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
    @Column(name = "scenario_token", nullable = false, length = 255)
    public String getScenarioToken() {
        return scenarioToken;
    }

    public void setScenarioToken(String scenarioToken) {
        this.scenarioToken = scenarioToken;
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
    @Column(name = "release_type", nullable = true)
    public Integer getReleaseType() {
        return releaseType;
    }

    public void setReleaseType(Integer releaseType) {
        this.releaseType = releaseType;
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
        Scenarios scenarios = (Scenarios) o;
        return id == scenarios.id && Objects.equals(scenarioToken, scenarios.scenarioToken) && Objects.equals(name, scenarios.name) && Objects.equals(releaseType, scenarios.releaseType) && Objects.equals(createdAt, scenarios.createdAt) && Objects.equals(updatedAt, scenarios.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, scenarioToken, name, releaseType, createdAt, updatedAt);
    }
}
