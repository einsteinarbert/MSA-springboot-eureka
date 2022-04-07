package io.github.eureka.api.model.dto;

public class ChangePasswordDTO {
    private Long id;
    private String password;
    private String confirmPassword;

    public ChangePasswordDTO() {
    }

    public ChangePasswordDTO(Long id, String password, String confirmPassword) {
        this.id = id;
        this.password = password;
        this.confirmPassword = confirmPassword;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
