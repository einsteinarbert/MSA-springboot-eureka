package io.github.eureka.api.model.form;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ChangePasswordForm {
    @NotNull(message = "userId must not be null")
    private Long id;
    @NotNull(message = "password must not be null")
    private String password;
    @NotNull(message = "confirmPassword must not be null")
    private String confirmPassword;
}
