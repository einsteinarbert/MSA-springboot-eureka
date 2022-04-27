package jp.co.mindshift.ayakashi.api.repo;

import jp.co.mindshift.ayakashi.api.model.GachaCharacters;
import jp.co.mindshift.ayakashi.api.model.Gachas;
import jp.co.mindshift.ayakashi.api.model.UserItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GachasRepository extends JpaRepository<Gachas, Long> {
	@Query(
	value = "select u from UserItems u where u.id = :userItemId and u.userId = :userId"
	)
    UserItems getGachaByUserItem(Long userId, Long userItemId);
	@Query(value = "select gtc from GachaCharacters gtc where gtc.gachaId = :gachaId")
	List<GachaCharacters> listGachaById(Long gachaId);
	
	@Query(value = "select gc from Gachas gc where  COALESCE(gc.startDate, current_date) <= current_date and COALESCE(gc.endDate, current_date) >= current_date")
	List<Gachas> getListGacha();
	
}
