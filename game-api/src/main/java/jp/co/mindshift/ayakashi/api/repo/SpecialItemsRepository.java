package jp.co.mindshift.ayakashi.api.repo;

import jp.co.mindshift.ayakashi.api.model.SpecialItems;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpecialItemsRepository extends JpaRepository<SpecialItems, Long> {
	SpecialItems getSpecialItemsBySpecialItemType(String itemType);
}
