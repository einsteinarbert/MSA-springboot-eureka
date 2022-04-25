package io.github.eureka.api.model.form;

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
	@NotNull(message = "deviceId must not be null")
	String deviceId;
	String name;
	Date birthday;
	@NotNull(message = "username must not be null")
	String username;
	Integer gender;
}
