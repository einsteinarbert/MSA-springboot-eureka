package jp.co.mindshift.ayakashi.api.repo;

import jp.co.mindshift.ayakashi.api.model.LoginRewardOptions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginRewardOptionsRepository extends JpaRepository<LoginRewardOptions, Long> {
	LoginRewardOptions findByDay(Integer day);
}
