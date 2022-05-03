package jp.co.mindshift.ayakashi.api.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CharacterDTO {
	private Long id;
	private Long itemId;
	private String characterToken;
	private String name;
	private Long skillId;
	private Long growthTypeId;
	private String situation;
	private Integer genderType;
	private Integer getRoute;
	private String stand;
	private String thumbnail;
	private String icon;
	private String skillGage;
	private String cutin;
	private String faceSmileId;
	private String faceSadId;
	private String faceAngryId;
	private String faceSurpriceId;
	private Date startDate;
	private Date endDate;
	private Timestamp createdAt;
	private Timestamp updatedAt;
}
