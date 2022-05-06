package jp.co.mindshift.ayakashi.api.repo;

import jp.co.mindshift.ayakashi.api.model.PresentBoxItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PresentBoxesItemsRepository extends JpaRepository<PresentBoxItems, Long> {
}
