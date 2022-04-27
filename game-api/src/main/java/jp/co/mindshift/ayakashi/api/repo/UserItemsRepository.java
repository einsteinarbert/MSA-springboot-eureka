package jp.co.mindshift.ayakashi.api.repo;

import jp.co.mindshift.ayakashi.api.model.UserItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Project: MSA-springboot-eureka.<br/>
 * Des: <br/>
 * User: HieuTT<br/>
 * Date: 03/04/2022<br/>
 * Time: 18:30<br/>
 */
 @Repository
public interface UserItemsRepository extends JpaRepository<UserItems, Long> {
	UserItems findUserItemsByUserIdAndItemId(Long userId, Long itemId);
	
	UserItems findUserItemsByUserIdAndItemType(Long userId, String itemType);
}
