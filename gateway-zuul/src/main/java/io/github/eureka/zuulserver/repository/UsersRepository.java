package io.github.eureka.zuulserver.repository;

import io.github.eureka.zuulserver.model.Users;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface UsersRepository extends CrudRepository<Users, Long> {
    Users findByUsernameAndStatus(String username, Integer status);
    Users findByUsernameAndStatusIn(String username, List<Integer> status);
    List<Users> findByDeviceIdAndStatusIn(String deviceId, List<Integer> status);
    Optional<Users> findByRefreshTokenAndStatusIn(String token, List<Integer> status);
}
