package com.bunbusoft.ayakashi.service;

import com.bunbusoft.ayakashi.service.dto.PasswordForgotDTO;
import org.springframework.validation.BindingResult;

import javax.servlet.http.HttpServletRequest;

public interface RegistrationService {
    String sendMailToResetPassword(BindingResult result, HttpServletRequest request, PasswordForgotDTO form);
}
