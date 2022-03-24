package com.bunbusoft.ayakashi.service;

import com.bunbusoft.ayakashi.service.dto.object.Mail;

public interface EmailService {
    void sendEmail(Mail mail);
}
