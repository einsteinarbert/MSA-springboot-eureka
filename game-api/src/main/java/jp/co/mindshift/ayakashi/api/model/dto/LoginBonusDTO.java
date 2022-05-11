package jp.co.mindshift.ayakashi.api.model.dto;

import jp.co.mindshift.ayakashi.api.model.LoginRewardItems;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginBonusDTO {
	private Long id;
	private Date startDate;
	private Date endDate;
	private Integer day;
	private Integer claim;
	private Long userId;
	private Integer toDay;
	private List<LoginRewardItemDTO> items;
}
