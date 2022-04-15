package io.github.eureka.api.repo;

import io.github.eureka.api.model.Characters;
import io.github.eureka.api.model.UserWallets;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CharactersRepository extends JpaRepository<Characters, Long> {
//    @Query(value = "select c from UserWallets c where c.id in ?1")
//    List<CharactersUserDTO> findAllCharacterByUser(String userId);
}
