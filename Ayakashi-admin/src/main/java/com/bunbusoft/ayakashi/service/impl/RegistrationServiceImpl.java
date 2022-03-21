package com.bunbusoft.ayakashi.service.impl;

import com.bunbusoft.ayakashi.domain.Administrators;
import com.bunbusoft.ayakashi.repository.AdministratorsRepository;
import com.bunbusoft.ayakashi.service.EmailService;
import com.bunbusoft.ayakashi.service.RegistrationService;
import com.bunbusoft.ayakashi.service.dto.Mail;
import com.bunbusoft.ayakashi.service.dto.PasswordForgotDTO;
import com.bunbusoft.ayakashi.service.dto.PasswordResetToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.Email;
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
            return "forgot-password";
        }
        return null;

//        Administrators user = administratorsRepository.findByEmail(form.getEmail());
//        if (user == null){
//            result.rejectValue("email", null, "We could not find an account for that e-mail address.");
//            return "forgot-password";
//        }
//
//        PasswordResetToken token = new PasswordResetToken();
//        token.setToken(UUID.randomUUID().toString());
//        token.setUser(user);
//        token.setExpiryDate(30);
//        tokenRepository.save(token);
//
//        Mail mail = new Mail();
//        mail.setFrom("no-reply@memorynotfound.com");
//        mail.setTo(user.getEmail());
//        mail.setSubject("Password reset request");
//
//        Map<String, Object> model = new HashMap<>();
//        model.put("token", token);
//        model.put("user", user);
//        model.put("signature", "https://memorynotfound.com");
//        String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
//        model.put("resetUrl", url + "/reset-password?token=" + token.getToken());
//        mail.setModel(model);
//        emailService.sendEmail(mail);
//        return "redirect:/forgot-password?success";
    }
}
