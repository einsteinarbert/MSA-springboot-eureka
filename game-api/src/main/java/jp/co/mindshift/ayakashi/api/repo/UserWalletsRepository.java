package jp.co.mindshift.ayakashi.api.repo;

import jp.co.mindshift.ayakashi.api.model.UserWallets;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.persistence.LockModeType;
import java.util.List;

/**
 * Project: MSA-springboot-eureka.<br/>
 * Des: <br/>
 * User: HieuTT<br/>
 * Date: 03/04/2022<br/>
 * Time: 18:19<br/>
 */
public interface UserWalletsRepository extends CrudRepository<UserWallets, Long> {
    @Lock(LockModeType.OPTIMISTIC_FORCE_INCREMENT)
    @Query(value = "select c from UserWallets c where c.id in ?1")
    List<UserWallets> findAllByIdWithLock(List<Long> ids);
    
    UserWallets findUserWalletsByUserIdAndWalletId(Long userId, Long walletId);
}
