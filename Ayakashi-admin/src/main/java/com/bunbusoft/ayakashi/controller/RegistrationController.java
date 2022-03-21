package com.bunbusoft.ayakashi.controller;

import com.bunbusoft.ayakashi.domain.Administrators;
import com.bunbusoft.ayakashi.repository.AdministratorsRepository;
import com.bunbusoft.ayakashi.service.AdministratorsService;
import com.bunbusoft.ayakashi.service.RegistrationService;
import com.bunbusoft.ayakashi.service.SecurityService;
import com.bunbusoft.ayakashi.service.dto.PasswordForgotDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
public class RegistrationController {
    @Autowired
    private SecurityService securityService;
    @Autowired
    RegistrationService registrationService;

    @ModelAttribute("forgotPasswordForm")
    public PasswordForgotDTO forgotPasswordDto() {
        return new PasswordForgotDTO();
    }

    @GetMapping("/registration/login")
    public String login() {
        if (securityService.isAuthenticated()) {
            return "redirect:/pages/dashboard";
        }
        return "registration/login";
    }

    @GetMapping("/registration/forgot-password")
    public String forgotPassword() {
        return "registration/forgot-password-form";
    }

    @PostMapping("/registration/forgot-password")
    public String processForgotPasswordForm(@ModelAttribute("forgotPasswordForm") @Valid PasswordForgotDTO form,
            BindingResult result,
            HttpServletRequest request) {
        return registrationService.sendMailToResetPassword(result, request, form);
    }
}
