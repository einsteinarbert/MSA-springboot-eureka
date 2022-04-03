package io.github.eureka.api.repo;

import io.github.eureka.api.model.UserItems;
import org.springframework.data.repository.CrudRepository;

/**
 * Project: MSA-springboot-eureka.<br/>
 * Des: <br/>
 * User: HieuTT<br/>
 * Date: 03/04/2022<br/>
 * Time: 18:30<br/>
 */
public interface UserItemsRepository extends CrudRepository<UserItems, Long> {
}
