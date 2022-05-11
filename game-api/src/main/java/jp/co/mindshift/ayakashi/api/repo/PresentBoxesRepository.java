package jp.co.mindshift.ayakashi.api.repo;

import jp.co.mindshift.ayakashi.api.model.PresentBoxes;
import jp.co.mindshift.ayakashi.api.model.UserWallets;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface PresentBoxesRepository extends JpaRepository<PresentBoxes, Long> {
	List<PresentBoxes> findPresentBoxesByUserIdOrderByCreatedAtAsc(Long userId);
	@Query(value = "select count(id) from present_boxes where user_id = :userId and generatable_id = :optionId and generatable_type = 'LOGIN_REWARDS' " +
	 "and cast(created_at as date) = :toDate", nativeQuery = true)
	Long checkPresentBoxes(Long userId, Long optionId, String toDate);
}
