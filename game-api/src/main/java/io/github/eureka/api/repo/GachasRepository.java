package io.github.eureka.api.repo;

import io.github.eureka.api.model.GachaCharacters;
import io.github.eureka.api.model.Gachas;
import io.github.eureka.api.model.UserItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GachasRepository extends JpaRepository<Gachas, Long> {
	@Query(
	value = "select * from user_items where id = :userItemId and user_id = :userId", nativeQuery = true
	)
	UserItems getGachaByUserItem(Long userId, Long userItemId);
	@Query(value = "select gtc.* from user_items ui inner join items i ON ui.item_id = i.id and i.item_type = 'GACHATICKET'\n" +
			"    inner join gachas gt on i.id = gt.item_id\n" +
			"inner join gacha_characters gtc on gt.id = gtc.gacha_id;", nativeQuery = true)
	List<GachaCharacters> listGachaById(Long userItemId);
}
