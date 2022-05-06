package jp.co.mindshift.ayakashi.api.repo;

import jp.co.mindshift.ayakashi.api.model.PresentBoxes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PresentBoxesRepository extends JpaRepository<PresentBoxes, Long> {
	List<PresentBoxes> findPresentBoxesByUserIdOrderByCreatedAtAsc(Long userId);
}
