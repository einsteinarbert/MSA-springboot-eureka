package com.bunbusoft.ayakashi.service.impl;

import com.bunbusoft.ayakashi.domain.Administrators;
import com.bunbusoft.ayakashi.repository.AdministratorsRepository;
import com.bunbusoft.ayakashi.service.EmailService;
import com.bunbusoft.ayakashi.service.RegistrationService;
import com.bunbusoft.ayakashi.service.dto.Mail;
import com.bunbusoft.ayakashi.service.dto.PasswordForgotDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class RegistrationServiceImpl implements RegistrationService {
    @Autowired
    AdministratorsRepository administratorsRepository;
    @Autowired
    EmailService emailService;
    @Override
    public String sendMailToResetPassword(BindingResult result, HttpServletRequest request, PasswordForgotDTO form) {
        if (result.hasErrors()){
            return "registration/forgot-password";
        }
        Administrators administrators = administratorsRepository.findByEmail(form.getEmail());
        if (administrators == null){
            result.rejectValue("email", null, "We could not find an account for that e-mail address.");
            return "registration/forgot-password";
        }

        administrators.setResetPasswordToken(UUID.randomUUID().toString());
        administrators.setResetPasswordSentAt(new Date());

        Mail mail = new Mail();
        mail.setFrom("no-reply@memorynotfound.com");
        mail.setTo(administrators.getEmail());
        mail.setSubject("Password reset request");

        Map<String, Object> model = new HashMap<>();
        model.put("admin", administrators);
        model.put("signature", "https://memorynotfound.com");
        String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
        model.put("resetUrl", url + "/reset-password?token=" + administrators.getResetPasswordToken());
        mail.setModel(model);
        emailService.sendEmail(mail);
        return "redirect:/forgot-password?success";
    }
}
