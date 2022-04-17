package io.github.eureka.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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
 * Time: 18:18<br/>
 */
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Voices {
    private long id;
    private String voiceToken;
    private int category;
    private String description;
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
    @Column(name = "voice_token", nullable = false, length = 255)
    public String getVoiceToken() {
        return voiceToken;
    }

    public void setVoiceToken(String voiceToken) {
        this.voiceToken = voiceToken;
    }

    @Basic
    @Column(name = "category", nullable = false)
    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    @Basic
    @Column(name = "description", nullable = false, length = -1)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
        Voices voices = (Voices) o;
        return id == voices.id && category == voices.category && Objects.equals(voiceToken, voices.voiceToken) && Objects.equals(description, voices.description) && Objects.equals(createdAt, voices.createdAt) && Objects.equals(updatedAt, voices.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, voiceToken, category, description, createdAt, updatedAt);
    }
}
