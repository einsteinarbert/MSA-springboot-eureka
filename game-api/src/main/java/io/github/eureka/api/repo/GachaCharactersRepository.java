package io.github.eureka.api.repo;

import io.github.eureka.api.model.GachaCharacters;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GachaCharactersRepository extends JpaRepository<GachaCharacters, Long> {
	List<GachaCharacters> findAllByGachaId(Long gachaId);
}
