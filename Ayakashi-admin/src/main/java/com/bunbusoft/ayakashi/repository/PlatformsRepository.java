package com.bunbusoft.ayakashi.repository;

import com.bunbusoft.ayakashi.domain.Platforms;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlatformsRepository extends JpaRepository<Platforms, Long> {
    Platforms findByPlatFormToken(String platformToken);
}
