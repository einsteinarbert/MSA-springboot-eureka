package io.github.eureka.api.repo;

import io.github.eureka.api.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * Project: MSA-springboot-eureka.<br/>
 * Des: <br/>
 * User: HieuTT<br/>
 * Date: 03/04/2022<br/>
 * Time: 18:19<br/>
 */
public interface UsersRepository extends JpaRepository<Users, Long> {
    Optional<Users> findByIdAndStatusIn(Long aLong, List<Integer> statusLst);
    Optional<Users> findByUsernameAndStatusIn(String userName, List<Integer> statusLst);
    Optional<Users> findByNameAndStatusIn(String name, List<Integer> statusLst);
}
