package com.bunbusoft.ayakashi.service;

import com.bunbusoft.ayakashi.domain.Administrators;
import com.bunbusoft.ayakashi.service.dto.object.AdminRegistrationDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface AdministratorsService extends UserDetailsService {
    Administrators save(AdminRegistrationDTO registrationDto);

    void updatePassword(String updatedPassword, Long id);
}
