package jp.co.mindshift.ayakashi.api.model.form;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserForm {
	Long id;
	@NotNull(message = "deviceId.null")
	String deviceId;
	String name;
	Date birthday;
	@NotNull(message = "username.null")
	String username;
	@NotNull(message = "gender.null")
	Integer gender;
}
