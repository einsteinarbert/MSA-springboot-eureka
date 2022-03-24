package com.bunbusoft.ayakashi.controller;

import com.bunbusoft.ayakashi.service.RegistrationService;
import com.bunbusoft.ayakashi.service.SecurityService;
import com.bunbusoft.ayakashi.service.dto.object.PasswordForgotDTO;
import com.bunbusoft.ayakashi.service.dto.object.PasswordResetDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
public class RegistrationController {

    private SecurityService securityService;
    private RegistrationService registrationService;

    public RegistrationController(SecurityService securityService, RegistrationService registrationService) {
        this.securityService = securityService;
        this.registrationService = registrationService;
    }

    @ModelAttribute("forgotPasswordForm")
    public PasswordForgotDTO forgotPasswordDto() {
        return new PasswordForgotDTO();
    }

    @ModelAttribute("passwordResetForm")
    public PasswordResetDTO passwordResetDTO() {
        return new PasswordResetDTO();
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


    @GetMapping("/registration/reset-password")
    public String displayResetPasswordPage(@RequestParam(required = false) String token,
                                           Model model) {
        return registrationService.getDisplayResetPasswordPage(token, model);
    }

    @PostMapping("/registration/reset-password")
    public String resetPasswordProcess(@ModelAttribute("passwordResetForm") @Valid PasswordResetDTO form,
                                       BindingResult result,
                                       RedirectAttributes redirectAttributes) {
        return registrationService.resetPassword(result, redirectAttributes, form);
    }
}
