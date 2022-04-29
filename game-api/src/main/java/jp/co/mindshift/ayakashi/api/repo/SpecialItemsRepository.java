package jp.co.mindshift.ayakashi.api.repo;

import jp.co.mindshift.ayakashi.api.model.SpecialItems;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SpecialItemsRepository extends JpaRepository<SpecialItems, Long> {
	List<SpecialItems> getSpecialItemsBySpecialItemType(Integer itemType);
}
