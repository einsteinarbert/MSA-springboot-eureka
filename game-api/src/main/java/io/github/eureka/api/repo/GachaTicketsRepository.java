package io.github.eureka.api.repo;

import io.github.eureka.api.model.GachaTickets;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GachaTicketsRepository extends JpaRepository<GachaTickets, Long> {
}
