package jp.co.mindshift.ayakashi.api.model.dto;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class LoginRewardItemEntity {
	@Id
	@Column(name = "id")
	private Long id;
	@Basic
	@Column(name = "item_type")
	private String itemType;
	@Basic
	@Column(name = "amount")
	private Integer amount;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getItemType() {
		return itemType;
	}

	public void setItemType(String itemType) {
		this.itemType = itemType;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}
}
