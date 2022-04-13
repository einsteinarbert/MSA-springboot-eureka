package io.github.eureka.api.repo;

import io.github.eureka.api.model.GachaCharacters;
import io.github.eureka.api.model.Gachas;
import io.github.eureka.api.model.UserItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface GachasRepository extends JpaRepository<Gachas, Long> {
	@Query(
	value = "select u from UserItems u where u.id = :userItemId and u.userId = :userId"
	)
	UserItems getGachaByUserItem(Long userId, Long userItemId);
	@Query(value = "select gtc from UserItems ui inner join Items i ON ui.itemId = i.id and i.itemType = 'COIN_GACHA'\n" +
			"    inner join Gachas gt on i.id = gt.itemId\n" +
			"inner join GachaCharacters gtc on gt.id = gtc.gachaId")
	List<GachaCharacters> listGachaById(Long userItemId);
}
