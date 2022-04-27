package jp.co.mindshift.ayakashi.api.model.dto;

import lombok.*;

import java.sql.Date;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
	private Long id;
	private String username;
	private String name;
	private Date birthday;
	private Integer age;
	private int status;
	private Timestamp createdAt;
	private Timestamp updatedAt;
	private String deviceId;
	private Long characterId;
	private Long backgroundId;
	private Long stage;
	private Long rankId;
	private Long mypageCharacterId;
	
}
