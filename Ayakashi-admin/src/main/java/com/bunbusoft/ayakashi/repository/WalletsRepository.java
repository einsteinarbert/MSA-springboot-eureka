package com.bunbusoft.ayakashi.repository;

import com.bunbusoft.ayakashi.domain.Wallets;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WalletsRepository extends JpaRepository<Wallets, Long> {
}
