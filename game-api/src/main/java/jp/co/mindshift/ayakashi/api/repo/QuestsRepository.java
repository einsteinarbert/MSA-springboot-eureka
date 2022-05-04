package jp.co.mindshift.ayakashi.api.repo;

import jp.co.mindshift.ayakashi.api.model.Quests;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestsRepository extends JpaRepository<Quests, Long> {
	List<Quests> findAllByIdIn(List<Long> ids);
	List<Quests> findByIdNotIn(List<Long> ids);
}
