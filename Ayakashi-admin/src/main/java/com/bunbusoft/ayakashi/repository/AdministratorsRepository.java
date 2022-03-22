package com.bunbusoft.ayakashi.repository;

import com.bunbusoft.ayakashi.domain.Administrators;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdministratorsRepository extends JpaRepository<Administrators, Long> {
    Administrators findByEmail(String email);
    Administrators findByResetPasswordToken(String token);
}
