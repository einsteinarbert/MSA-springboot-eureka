package com.bunbusoft.ayakashi.service.impl;

import com.bunbusoft.ayakashi.domain.Administrators;
import com.bunbusoft.ayakashi.repository.AdministratorsRepository;
import com.bunbusoft.ayakashi.service.AdministratorsService;
import com.bunbusoft.ayakashi.service.EmailService;
import com.bunbusoft.ayakashi.service.RegistrationService;
import com.bunbusoft.ayakashi.service.dto.Mail;
import com.bunbusoft.ayakashi.service.dto.PasswordForgotDTO;
import com.bunbusoft.ayakashi.service.dto.PasswordResetDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Service
public class RegistrationServiceImpl implements RegistrationService {
    private AdministratorsRepository administratorsRepository;
    private EmailService emailService;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private AdministratorsService administratorsService;
    @Value("${spring.mail.username}")
    String sendMailFrom;

    public RegistrationServiceImpl(AdministratorsRepository administratorsRepository, EmailService emailService, BCryptPasswordEncoder bCryptPasswordEncoder, AdministratorsService administratorsService) {
        this.administratorsRepository = administratorsRepository;
        this.emailService = emailService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.administratorsService = administratorsService;
    }

    @Override
    public String sendMailToResetPassword(BindingResult result, HttpServletRequest request, PasswordForgotDTO form) {
        if (result.hasErrors()){
            return "registration/forgot-password";
        }
        Administrators administrators = administratorsRepository.findByEmail(form.getEmail());
        if (administrators == null){
            result.rejectValue("templates/email", null, "We could not find an account for that e-mail address.");
            return "registration/forgot-password";
        }

        administrators.setResetPasswordToken(UUID.randomUUID().toString());
        administrators.setResetPasswordSentAt(new Date());

        Mail mail = new Mail();
        mail.setFrom(sendMailFrom);
        mail.setTo(administrators.getEmail());
        mail.setSubject("Password reset request");
        Map<String, Object> model = new HashMap<>();
        model.put("admin", administrators);
        model.put("signature", "Signature");
        String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
        model.put("resetUrl", url + "/registration/reset-password?token=" + administrators.getResetPasswordToken());
        mail.setModel(model);
        emailService.sendEmail(mail);
        administratorsRepository.save(administrators);
        return "redirect:/registration/forgot-password?success";
    }

    @Override
    public String resetPassword(BindingResult result, RedirectAttributes redirectAttributes, PasswordResetDTO form) {
        if (result.hasErrors()){
            redirectAttributes.addFlashAttribute(BindingResult.class.getName() + ".passwordResetForm", result);
            redirectAttributes.addFlashAttribute("passwordResetForm", form);
            return "redirect:/registration/reset-password?token=" + form.getToken();
        }
        Administrators administrators = administratorsRepository.findByResetPasswordToken(form.getToken());
        String updatedPassword = bCryptPasswordEncoder.encode(form.getPassword());
        administratorsService.updatePassword(updatedPassword, administrators.getId());
        administrators.setResetPasswordToken(null);
        administrators.setResetPasswordSentAt(null);
        return "redirect:/registration/reset-password?token=" + form.getToken() + "&success";
    }

    @Override
    public String getDisplayResetPasswordPage(String token, Model model) {
        Administrators administrators = administratorsRepository.findByResetPasswordToken(token);
        if (administrators == null){
            model.addAttribute("error", "パスワードリセットトークンが見つかりませんでした。");
        } else if (this.isExpired(administrators.getResetPasswordSentAt(), 30)){
            model.addAttribute("error", "トークンの有効期限が切れています。新しいパスワードのリセットをリクエストしてください。");
        } else {
            model.addAttribute("token", token);
        }
        return "registration/reset-password-form";
    }

    private Boolean isExpired(Date resetPasswordSentAt, int minutes){
        Calendar expiredToken = Calendar.getInstance();
        expiredToken.setTime(resetPasswordSentAt);
        expiredToken.add(Calendar.MINUTE, minutes);
        if(new Date().after(expiredToken.getTime()))
            return true;
        else
            return false;
    }

}
