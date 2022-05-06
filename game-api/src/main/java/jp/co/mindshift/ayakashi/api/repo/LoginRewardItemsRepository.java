package jp.co.mindshift.ayakashi.api.repo;

import jp.co.mindshift.ayakashi.api.model.LoginRewardItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoginRewardItemsRepository extends JpaRepository<LoginRewardItems, Long> {
	List<LoginRewardItems> findAllByLoginRewardOptionId(Long rewardOptionId);
}
