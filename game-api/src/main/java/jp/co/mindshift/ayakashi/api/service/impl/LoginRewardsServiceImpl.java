package jp.co.mindshift.ayakashi.api.service.impl;

import jp.co.mindshift.ayakashi.api.common.DataUtil;
import jp.co.mindshift.ayakashi.api.model.*;
import jp.co.mindshift.ayakashi.api.model.dto.LoginBonusDTO;
import jp.co.mindshift.ayakashi.api.model.dto.LoginRewardItemEntity;
import jp.co.mindshift.ayakashi.api.model.dto.ResponseDTO;
import jp.co.mindshift.ayakashi.api.model.entity.LoginBonusEntity;
import jp.co.mindshift.ayakashi.api.repo.*;
import jp.co.mindshift.ayakashi.api.service.BaseService;
import jp.co.mindshift.ayakashi.api.service.LoginRewardsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
@AllArgsConstructor
public class LoginRewardsServiceImpl extends BaseService implements LoginRewardsService {
	@PersistenceContext
	EntityManager em;
	private final PresentBoxesRepository presentBoxesRepository;
	private final LoginRewardOptionsRepository loginRewardOptionsRepository;
	private final LoginRewardItemsRepository loginRewardItemsRepository;
	private final UserItemsRepository userItemsRepository;
	private final PresentBoxesItemsRepository presentBoxesItemsRepository;
	private static final String sql = "select row_number() over (order by lro.day) as id, lr.start_date, lr.end_date, lro.day, " +
	 						"lri.item_id, IF(pb.user_id is null, 0, 1) claim, :userId as user_id,\n" +
			"if(lro.day = :toDate, 1, 0) to_day \n" +
			"from login_rewards lr inner join login_reward_options lro on lr.id = lro.login_reward_id \n" +
			"left join  (select * from present_boxes pb order by pb.created_at desc limit 7) pb on pb.generatable_id = lro.id and pb.generatable_type = 'LOGIN_REWARDS' and pb.user_id = :userId\n" +
			"where curdate() between cast(lr.start_date as date) and cast(lr.end_date as date) and lr.login_reward_type = 1";
	private static final String sqlGetLoginItem = "select row_number() over (order by si.special_item_type) as id,  IF(si.special_item_type = 1, 'JEWEL', " +
	 "IF(si.special_item_type = 2, 'GACHA_TICKET', " +
	  "IF(si.special_item_type = 3, 'POWER_UP_TICKET', " +
	   "IF(si.special_item_type = 4, 'PUZZLE_ITEM', " +
	    "IF(si.special_item_type = 5, 'COIN', " +
	     "IF(si.special_item_type = 6, 'MEDAL', " +
	      "IF(si.special_item_type = 7, 'HEART', " +
	       "IF(si.special_item_type = 8, 'STAMINA', '')))))))) as item_type, lri.number amount " +
	        "from login_reward_items lri inner join special_items si on lri.item_id = si.item_id where lri.login_reward_option_id = :loginRewardOption";

	@Override
	public ResponseDTO<?> getList(Long userId) {
		//Startdate = ngay nhan dau tien cua user
		Integer toDate = 1;
		List<PresentBoxes> presentBoxesList = presentBoxesRepository.findPresentBoxesByUserIdOrderByCreatedAtAsc(userId);
		if (!DataUtil.isNullOrEmpty(presentBoxesList)) {
			Long diff = new Date().getTime() - presentBoxesList.get(0).getCreatedAt().getTime();
			TimeUnit time = TimeUnit.DAYS;
			Long diffrence = time.convert(diff, TimeUnit.MILLISECONDS);
			toDate = Math.toIntExact(diffrence) % 7;
		}
		List<LoginBonusEntity> loginBonusEntity = em.createNativeQuery(sql, LoginBonusEntity.class)
				.setParameter("userId", userId)
				.setParameter("toDate", toDate)
				.getResultList();
		//Lay danh sach item
		List<LoginBonusDTO> bonusDTOS = super.mapList(loginBonusEntity, LoginBonusDTO.class);
		for(LoginBonusDTO bonus : bonusDTOS){
			List<LoginRewardItemEntity> items =  em.createNativeQuery(sqlGetLoginItem, LoginRewardItemEntity.class)
					.setParameter("userId", userId)
					.setParameter("toDate", toDate)
					.getResultList();
			bonus.setItems(items);
		}
		
		//save today item - presentBoxes
		LoginRewardOptions toDay = loginRewardOptionsRepository.findByDay(toDate);
		List<LoginRewardItems> lstTodayItem = loginRewardItemsRepository.findAllByLoginRewardOptionId(toDay.getId());
		List<PresentBoxItems> lstPresentItem = new ArrayList<>();

		PresentBoxes toDayPresent = new PresentBoxes();
		toDayPresent.setUserId(userId);
		toDayPresent.setGeneratableType("LOGIN_REWARDS");
		toDayPresent.setGeneratableId(toDay.getId());
		toDayPresent.setCreatedAt(new Date());
		toDayPresent.setUpdatedAt(new Date());
		toDayPresent.setStatus(1);
		toDayPresent = presentBoxesRepository.save(toDayPresent);
		for (LoginRewardItems bonusItem : lstTodayItem) {
			PresentBoxItems presentBoxItems = new PresentBoxItems();
			presentBoxItems.setPresentBoxId(toDayPresent.getId());
			presentBoxItems.setItemId(bonusItem.getItemId());
			presentBoxItems.setItemNumber(bonusItem.getNumber());
			presentBoxItems.setCreatedAt(new Date());
			presentBoxItems.setUpdatedAt(new Date());
			lstPresentItem.add(presentBoxItems);

			UserItems existItem = userItemsRepository.findUserItemsByUserIdAndItemId(userId, bonusItem.getItemId());
			if (DataUtil.isNullOrEmpty(existItem)) {
				UserItems newItem = new UserItems();
				newItem.setUserId(userId);
				newItem.setItemId(bonusItem.getItemId());
				newItem.setNumber(Long.valueOf(bonusItem.getNumber()));
				newItem.setItemType("SPECIAL_ITEMS");
				newItem.setCreatedAt(new Date());
				newItem.setUpdatedAt(new Date());
				userItemsRepository.save(newItem);
			} else {
				existItem.setNumber(existItem.getNumber() + Long.valueOf(bonusItem.getNumber()));
				existItem.setUpdatedAt(new Date());
				userItemsRepository.save(existItem);
			}
		}
		presentBoxesItemsRepository.saveAll(lstPresentItem);
		return ResponseDTO.success(loginBonusEntity);
	}
}
