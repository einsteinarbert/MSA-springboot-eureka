package io.github.eureka.api.repo;

import io.github.eureka.api.model.SpecialItems;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpecialItemsRepository extends JpaRepository<SpecialItems, Long> {
	SpecialItems getSpecialItemsBySpecialItemType(String itemType);
}
