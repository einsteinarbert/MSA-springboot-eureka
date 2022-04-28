package jp.co.mindshift.ayakashi.api.model.form;

import jp.co.mindshift.ayakashi.api.model.dto.GachaCharacterDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GachasForm {
	private Long id;
	private Long itemId;
	private String name;
	private Integer gachaType;
	private Long paymentMethodId;
	private Integer price;
	private Long paymentMethodId2;
	private Integer price2;
	private String thumbnail;
	private String icon;
	private Date startDate;
	private Date endDate;
	private Timestamp createdAt;
	private Timestamp updatedAt;
	private List<GachaCharacterDTO> gachaCharacterDTOList;
}