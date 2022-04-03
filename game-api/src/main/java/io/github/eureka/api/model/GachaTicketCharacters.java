package io.github.eureka.api.model;

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
 * Date: 03/04/2022<br/>
 * Time: 18:17<br/>
 */
@Entity
@Table(name = "gacha_ticket_characters")
public class GachaTicketCharacters {
    private long id;
    private long gachaTicketId;
    private long characterId;
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
    @Column(name = "gacha_ticket_id", nullable = false)
    public long getGachaTicketId() {
        return gachaTicketId;
    }

    public void setGachaTicketId(long gachaTicketId) {
        this.gachaTicketId = gachaTicketId;
    }

    @Basic
    @Column(name = "character_id", nullable = false)
    public long getCharacterId() {
        return characterId;
    }

    public void setCharacterId(long characterId) {
        this.characterId = characterId;
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
        GachaTicketCharacters that = (GachaTicketCharacters) o;
        return id == that.id && gachaTicketId == that.gachaTicketId && characterId == that.characterId && Objects.equals(createdAt, that.createdAt) && Objects.equals(updatedAt, that.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, gachaTicketId, characterId, createdAt, updatedAt);
    }
}
