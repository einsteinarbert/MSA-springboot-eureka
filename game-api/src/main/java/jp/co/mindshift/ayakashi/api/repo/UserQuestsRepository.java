package jp.co.mindshift.ayakashi.api.repo;

import jp.co.mindshift.ayakashi.api.model.UserQuests;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserQuestsRepository extends JpaRepository<UserQuests, Long> {
}
