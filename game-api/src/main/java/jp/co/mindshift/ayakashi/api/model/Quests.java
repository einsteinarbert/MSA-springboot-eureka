package jp.co.mindshift.ayakashi.api.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * Project: MSA-springboot-eureka.<br/>
 * Des: <br/>
 * User: HieuTT<br/>
 * Date: 20/04/2022<br/>
 */
@Entity
@Table(name = "quests")
public class Quests {
    private long id;
    private String name;
    private String description;
    private String scenarioFile;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    @Id
    @Column(name = "id")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "scenario_file")
    public String getScenarioFile() {
        return scenarioFile;
    }

    public void setScenarioFile(String scenarioFile) {
        this.scenarioFile = scenarioFile;
    }

    @Basic
    @Column(name = "created_at")
    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    @Basic
    @Column(name = "updated_at")
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
        Quests quests = (Quests) o;
        return id == quests.id && Objects.equals(name, quests.name) && Objects.equals(description, quests.description) && Objects.equals(scenarioFile, quests.scenarioFile) && Objects.equals(createdAt, quests.createdAt) && Objects.equals(updatedAt, quests.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, scenarioFile, createdAt, updatedAt);
    }
}