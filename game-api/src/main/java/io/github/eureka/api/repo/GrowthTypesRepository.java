package io.github.eureka.api.repo;

import io.github.eureka.api.model.GrowthTypes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GrowthTypesRepository extends JpaRepository<GrowthTypes, Long> {
}
