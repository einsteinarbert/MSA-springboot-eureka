package io.github.eureka.api.model.dto;

public class SpinGachaDTO {
	private Long userItemId;
	private Long userId;

	public SpinGachaDTO() {
	}

	public SpinGachaDTO(Long userItemId, Long userId) {
		this.userItemId = userItemId;
		this.userId = userId;
	}

	public Long getUserItemId() {
		return userItemId;
	}

	public void setUserItemId(Long userItemId) {
		this.userItemId = userItemId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
}
