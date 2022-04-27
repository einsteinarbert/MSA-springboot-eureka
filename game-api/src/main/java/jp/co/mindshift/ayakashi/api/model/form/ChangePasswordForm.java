package jp.co.mindshift.ayakashi.api.model.form;

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
    @NotNull(message = "userId.null")
    private Long id;
    @NotNull(message = "passwd.null")
    private String password;
    @NotNull(message = "confirmPassword.null")
    private String confirmPassword;
}
