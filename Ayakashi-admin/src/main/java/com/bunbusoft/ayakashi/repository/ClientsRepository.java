package com.bunbusoft.ayakashi.repository;

import com.bunbusoft.ayakashi.domain.Clients;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientsRepository extends JpaRepository<Clients, Long> {
    Clients findByClientToken(String clientToken);
}
