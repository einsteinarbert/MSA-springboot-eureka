package io.github.eureka.api.repo;

import io.github.eureka.api.model.Scenarios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScenariosRepository extends JpaRepository<Scenarios, Long> {
}
