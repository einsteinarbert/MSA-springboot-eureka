package io.github.eureka.api.repo;

import io.github.eureka.api.model.Users;
import org.springframework.data.repository.CrudRepository;

/**
 * Project: MSA-springboot-eureka.<br/>
 * Des: <br/>
 * User: HieuTT<br/>
 * Date: 03/04/2022<br/>
 * Time: 18:19<br/>
 */
public interface UsersRepository extends CrudRepository<Users, Long> {
}
