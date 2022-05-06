package jp.co.mindshift.ayakashi.api.model.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
public class LoginBonusEntity {
	@Id
	@Column(name = "id")
	private long id;
	@Basic
	@Column(name = "start_date")
	private Timestamp startDate;
	@Basic
	@Column(name = "end_date")
	private Timestamp endDate;
	@Basic
	@Column(name = "day")
	private int day;
	@Basic
	@Column(name = "item_id")
	private long itemId;
	@Basic
	@Column(name = "claim")
	private int claim;
	@Basic
	@Column(name = "user_id")
	private Long userId;
	@Basic
	@Column(name = "to_day")
	private int toDay;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Timestamp getStartDate() {
		return startDate;
	}

	public void setStartDate(Timestamp startDate) {
		this.startDate = startDate;
	}

	public Timestamp getEndDate() {
		return endDate;
	}

	public void setEndDate(Timestamp endDate) {
		this.endDate = endDate;
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public long getItemId() {
		return itemId;
	}

	public void setItemId(long itemId) {
		this.itemId = itemId;
	}

	public int getClaim() {
		return claim;
	}

	public void setClaim(int claim) {
		this.claim = claim;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public int getToDay() {
		return toDay;
	}

	public void setToDay(int toDay) {
		this.toDay = toDay;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		LoginBonusEntity that = (LoginBonusEntity) o;
		return id == that.id && day == that.day && itemId == that.itemId && claim == that.claim && toDay == that.toDay && Objects.equals(startDate, that.startDate) && Objects.equals(endDate, that.endDate) && Objects.equals(userId, that.userId);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, startDate, endDate, day, itemId, claim, userId, toDay);
	}
}
