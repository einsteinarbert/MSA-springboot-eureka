package jp.co.mindshift.ayakashi.api.repo;

import jp.co.mindshift.ayakashi.api.model.UserQuests;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface UserQuestsRepository extends JpaRepository<UserQuests, Long> {
    List<UserQuests> findAllByUserIdAndUpdatedAtBetweenAndStatusInAndTypeIs(Long uid, Date from, Date to, List<Integer> status, Integer type);
    List<UserQuests> findAllByUserIdAndStatusInAndTypeIs(Long uid, List<Integer> status, Integer type);
}
