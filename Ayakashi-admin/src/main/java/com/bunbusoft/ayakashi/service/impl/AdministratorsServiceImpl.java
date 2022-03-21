package com.bunbusoft.ayakashi.service.impl;

import com.bunbusoft.ayakashi.domain.Administrators;
import com.bunbusoft.ayakashi.repository.AdministratorsRepository;
import com.bunbusoft.ayakashi.service.AdministratorsService;
import com.bunbusoft.ayakashi.service.dto.AdminRegistrationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;

@Service
public class AdministratorsServiceImpl implements AdministratorsService {

    private AdministratorsRepository administratorsRepository;

//    @Autowired
//    private BCryptPasswordEncoder passwordEncoder;

    public AdministratorsServiceImpl(AdministratorsRepository administratorsRepository) {
        super();
        this.administratorsRepository = administratorsRepository;
    }

    @Override
    public Administrators save(AdminRegistrationDTO registrationDto) {
        return null;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Administrators user = administratorsRepository.findByEmail(username);
        if(user == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getEncryptedPassword(), mapRolesToAuthorities("ADMIN"));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(String role){
        return Collections.singleton(new SimpleGrantedAuthority(role));
    }
}
