package io.github.eureka.api.repo;

import io.github.eureka.api.model.UserWallets;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.LockModeType;
import java.util.List;
import java.util.Optional;

/**
 * Project: MSA-springboot-eureka.<br/>
 * Des: <br/>
 * User: HieuTT<br/>
 * Date: 03/04/2022<br/>
 * Time: 18:19<br/>
 */
public interface UserWalletsRepository extends JpaRepository<UserWallets, Long> {
    @Lock(LockModeType.OPTIMISTIC_FORCE_INCREMENT)
    @Query(value = "select c from UserWallets c where c.userId in ?1")
    List<UserWallets> findAllByUserIdWithLock(List<Long> ids);

    @Lock(LockModeType.OPTIMISTIC_FORCE_INCREMENT)
    @Query(value = "select c from UserWallets c where c.userId = ?1 and c.walletId = ?2")
    Optional<UserWallets> findByUserIdAndWalletIdWithLock(Long userId, Long walletId);
}
