package com.bunbusoft.ayakashi.service;

import com.bunbusoft.ayakashi.service.dto.object.PasswordForgotDTO;
import com.bunbusoft.ayakashi.service.dto.object.PasswordResetDTO;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

public interface RegistrationService {
    String sendMailToResetPassword(BindingResult result, HttpServletRequest request, PasswordForgotDTO form);

    String resetPassword(BindingResult result, RedirectAttributes redirectAttributes, PasswordResetDTO form);

    String getDisplayResetPasswordPage(String token, Model model);
}
