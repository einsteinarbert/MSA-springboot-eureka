package jp.co.mindshift.ayakashi.api.service.impl;

import jp.co.mindshift.ayakashi.api.common.Constant;
import jp.co.mindshift.ayakashi.api.common.DataUtil;
import jp.co.mindshift.ayakashi.api.common.MsgUtil;
import jp.co.mindshift.ayakashi.api.config.ActionUserHolder;
import jp.co.mindshift.ayakashi.api.model.Background;
import jp.co.mindshift.ayakashi.api.model.Characters;
import jp.co.mindshift.ayakashi.api.model.UserItems;
import jp.co.mindshift.ayakashi.api.model.Users;
import jp.co.mindshift.ayakashi.api.model.dto.ActionUserDTO;
import jp.co.mindshift.ayakashi.api.model.dto.BackgroundDTO;
import jp.co.mindshift.ayakashi.api.model.dto.CharacterDTO;
import jp.co.mindshift.ayakashi.api.model.dto.UserDataDTO;
import jp.co.mindshift.ayakashi.api.model.dto.UserDTO;
import jp.co.mindshift.ayakashi.api.model.entity.UserDataEntity;
import jp.co.mindshift.ayakashi.api.model.form.ChangePasswordForm;
import jp.co.mindshift.ayakashi.api.model.form.CreateUserForm;
import jp.co.mindshift.ayakashi.api.model.form.UserSettingForm;
import jp.co.mindshift.ayakashi.api.repo.BackgroundRepository;
import jp.co.mindshift.ayakashi.api.repo.CharactersRepository;
import jp.co.mindshift.ayakashi.api.repo.UserItemsRepository;
import jp.co.mindshift.ayakashi.api.repo.UsersRepository;
import jp.co.mindshift.ayakashi.api.securities.PBKDF2Encoder;
import jp.co.mindshift.ayakashi.api.service.BaseService;
import jp.co.mindshift.ayakashi.api.service.CommonService;
import jp.co.mindshift.ayakashi.api.service.UsersService;
import jp.co.mindshift.ayakashi.api.model.dto.ResponseDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.math.BigInteger;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UsersServiceImpl extends BaseService implements UsersService {
	@PersistenceContext
	EntityManager em;
	private final UsersRepository usersRepository;
	private final CommonService commonService;
	private final PBKDF2Encoder passwordEncoder;
	private CharactersRepository charactersRepository;
	private BackgroundRepository backgroundRepository;
	private UserItemsRepository userItemsRepository;
	private static final String userDataSQL = "select * from (select u.id, u.username, u.name, u.age, u.character_id, u.background_id, u.stage,\n" +
			"ifnull(uw.jewel_number, 0) jewel_number, ifnull(uw.jewel_bonus_number, 0) jewel_bonus_number, ifnull(uw.coin_number, 0) coin_number,\n" +
			"ifnull(stamina_number, 0) stamina_number,\n" +
			"ifnull(heart, 0) heart, ifnull(heart_30, 0) heart_30, ifnull(heart_60, 0) heart_60, IF(pb.user_id is null, 0, 1) login_bonus_flag, r.rank_token user_rank,		 \n" +
			"u.gender_type \n" +
			"from users u\n" +
			"left join(\n" +
			"select uw.user_id, sum(IF(w.wallet_type = 0 and w.jewel_type = 0, uw.number, 0)) jewel_number,\n" +
			"            sum(IF(w.wallet_type = 0 and w.jewel_type = 1, uw.number, 0)) jewel_bonus_number,\n" +
			"            sum(IF(w.wallet_type = 1, uw.number, 0)) coin_number \n" +
			"from user_wallets uw left join wallets w\n" +
			"    on uw.wallet_id = w.id and w.wallet_type IN (0, 1)\n" +
			"group by uw.user_id) uw ON u.id = uw.user_id\n" +
			"left join(select ui.user_id, sum( IF(ui.item_type = 'STAMINA', ui.number, 0) ) stamina_number,\n" +
			"                 sum( IF(ui.item_type = 'HEART', ui.number, 0) ) heart,\n" +
			"                 sum( IF(ui.item_type = 'HEART30', ui.number, 0) ) heart_30,\n" +
			"                 sum( IF(ui.item_type = 'HEART60', ui.number, 0) ) heart_60\n" +
			"                 from user_items ui where ui.item_type IN ('STAMINA', 'HEART', 'HEART30', 'HEART60')\n" +
			"    group by ui.user_id) ui ON u.id = ui.user_id \n" +
			 "left join (select user_id from present_boxes where cast(created_at as date) = current_date) pb on u.id = pb.user_id \n" +
			"left join ranks r on u.rank_id = r.id \n" +
			"where u.id = :userId ) T limit 1";

	private static final String userDataSQLDevice = "select * from (select u.id, u.username, u.name, u.age, u.character_id, u.background_id, u.stage,\n" +
			" ifnull(uw.jewel_number, 0) jewel_number, ifnull(uw.jewel_bonus_number, 0) jewel_bonus_number, ifnull(uw.coin_number, 0) coin_number,\n" +
			"ifnull(stamina_number, 0) stamina_number,\n" +
			"ifnull(heart, 0) heart, ifnull(heart_30, 0) heart_30, ifnull(heart_60, 0) heart_60, IF(pb.user_id is null, 0, 1) login_bonus_flag, r.rank_token user_rank, \n" +
			"u.gender_type \n" +
			"from users u \n" +
			"left join( \n" +
			"select uw.user_id, sum(IF(w.wallet_type = 0 and w.jewel_type = 0, uw.number, 0)) jewel_number,\n" +
			"            sum(IF(w.wallet_type = 0 and w.jewel_type = 1, uw.number, 0)) jewel_bonus_number,\n" +
			"            sum(IF(w.wallet_type = 1, uw.number, 0)) coin_number \n" +
			"from user_wallets uw left join wallets w\n" +
			"    on uw.wallet_id = w.id and w.wallet_type IN (0, 1)\n" +
			"group by uw.user_id) uw ON u.id = uw.user_id\n" +
			"left join(select ui.user_id, sum( IF(ui.item_type = 'STAMINA', ui.number, 0) ) stamina_number,\n" +
			"                 sum( IF(ui.item_type = 'HEART', ui.number, 0) ) heart,\n" +
			"                 sum( IF(ui.item_type = 'HEART30', ui.number, 0) ) heart_30,\n" +
			"                 sum( IF(ui.item_type = 'HEART60', ui.number, 0) ) heart_60\n" +
			"                 from user_items ui where ui.item_type IN ('STAMINA', 'HEART', 'HEART30', 'HEART60')\n" +
			"    group by ui.user_id) ui ON u.id = ui.user_id\n" +
			"left join (select user_id from present_boxes where cast(created_at as date) = current_date) pb on u.id = pb.user_id \n" +
			"left join ranks r on u.rank_id = r.id \n" +
			"where u.device_id = :deviceId ) T limit 1";

	@Override
	@Transactional
	public ResponseDTO<?> createUser(CreateUserForm users) {
		Optional<Users> deviceIdExisting = usersRepository.findByDeviceIdAndStatusIn(users.getDeviceId(), Arrays.asList(Constant.STATUS.ANONYMOUS, Constant.STATUS.REGITERED));
		Assert.isTrue(deviceIdExisting.isEmpty(), MsgUtil.getMessage("device.exist"));
		Optional<Users> existingName = usersRepository.findByNameAndStatusIn(users.getName(), Arrays.asList(Constant.STATUS.ANONYMOUS, Constant.STATUS.REGITERED));
		Assert.isTrue(existingName.isEmpty(), MsgUtil.getMessage("name.exist"));
		Characters defaultChar;
		Users newUser = super.map(users, Users.class);
		newUser.setAge(new Date(Calendar.getInstance().getTimeInMillis()), users.getBirthday());
		newUser.setStatus(Constant.STATUS.ANONYMOUS);
		newUser.setCreatedAt(new Timestamp(System.currentTimeMillis()));
		newUser.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
		newUser.setStage(1L);
		newUser = usersRepository.save(newUser);
		//Character default neu la nu
		if (null != users.getGender() && users.getGender() == 2) {
			defaultChar = charactersRepository.getCharactersByCharacterToken(Constant.CHARACTER_DEFAULT.FEMALE);
		} else {
			defaultChar = charactersRepository.getCharactersByCharacterToken(Constant.CHARACTER_DEFAULT.MALE);
		}
		UserItems defaultCharItem = new UserItems();
		defaultCharItem.setItemId(defaultChar.getItemId());
		defaultCharItem.setItemType(Constant.ITEMTYPE.CHARACTER);
		defaultCharItem.setLevel(1);
		defaultCharItem.setNumber(1L);
		defaultCharItem.setCreatedAt(new Timestamp(System.currentTimeMillis()));
		defaultCharItem.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
		defaultCharItem.setUserId(newUser.getId());
		defaultCharItem = userItemsRepository.save(defaultCharItem);
		newUser.setCharacterId(defaultCharItem.getId());
		newUser.setMypageCharacterId(defaultCharItem.getId());
		//Background default
		Background defaultBackground = backgroundRepository.getBackgroundByBackgroundToken(Constant.BACKGROUND_DEFAULT);
		UserItems defaultBackgroundItem = new UserItems();
		if (defaultBackground != null) {
			defaultBackgroundItem.setItemId(defaultBackground.getItemId());
		} else {
			defaultBackgroundItem.setItemId(-1L);
		}
		defaultBackgroundItem.setItemType(Constant.ITEMTYPE.BACKGROUND);
		defaultBackgroundItem.setNumber(1L);
		defaultBackgroundItem.setCreatedAt(new Timestamp(System.currentTimeMillis()));
		defaultBackgroundItem.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
		defaultBackgroundItem.setUserId(newUser.getId());
		defaultBackgroundItem = userItemsRepository.save(defaultBackgroundItem);
		newUser.setBackgroundId(defaultBackgroundItem.getId());
		usersRepository.save(newUser);
		return ResponseDTO.success(super.map(newUser, UserDTO.class));
	}

	@Override
	public ResponseDTO<?> getUserById(Long id) {
		return ResponseDTO.success(super.map(usersRepository.findByIdAndStatusIn(id, Arrays.asList(Constant.STATUS.ANONYMOUS, Constant.STATUS.REGITERED)).orElseThrow(
				() -> new IllegalArgumentException(MsgUtil.getMessage("user.info.null"))
		), UserDTO.class));
	}

	@Override
	public ResponseDTO<?> updateUser(Users users) {
		Users existingId = usersRepository.getById(users.getId());
		Assert.notNull(existingId, MsgUtil.getMessage("user.info.null"));
		Optional<Users> existingUser = usersRepository.findByUsernameAndStatusIn(users.getUsername(), Arrays.asList(Constant.STATUS.ANONYMOUS, Constant.STATUS.REGITERED));
		if (existingUser.isPresent()) {
			if (!existingUser.get().getId().equals(users.getId()))
				throw new IllegalArgumentException(MsgUtil.getMessage("userName.exist"));
		}
		Optional<Users> existingName = usersRepository.findByNameAndStatusIn(users.getName(), Arrays.asList(Constant.STATUS.ANONYMOUS, Constant.STATUS.REGITERED));
		if (existingName.isPresent()) {
			if (!existingName.get().getId().equals(users.getId()))
				throw new IllegalArgumentException(MsgUtil.getMessage("name.exist"));
		}
		users.setAge(new Date(Calendar.getInstance().getTimeInMillis()), users.getBirthday());
		users.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
		usersRepository.save(users);
		return ResponseDTO.success(super.map(users, UserDTO.class));
	}

	@Override
	public ResponseDTO<?> updateUserPassword(ChangePasswordForm users) {
		if (!users.getPassword().equals(users.getConfirmPassword())) {
			throw new IllegalArgumentException(MsgUtil.getMessage("password.notmatch"));
		}
		Users existingId = usersRepository.getById(users.getId());
		Assert.notNull(existingId, MsgUtil.getMessage("user.info.null"));
		existingId.setEncryptedPassword(passwordEncoder.encode(users.getPassword()));
		existingId.setStatus(Constant.STATUS.REGITERED);
		usersRepository.save(existingId);
		return ResponseDTO.success(super.map(existingId, UserDTO.class));
	}

	@Override
	public ResponseDTO<?> getAllUser() {
		List<Users> usersList = usersRepository.findAllByStatusIn(Arrays.asList(Constant.STATUS.ANONYMOUS, Constant.STATUS.REGITERED));
		return ResponseDTO.success(super.mapList(usersList, UserDTO.class));
	}

	@Override
	public UserDataDTO getDataUserInMyPage(Long userId) {
		UserDataEntity userDataEntity = (UserDataEntity) em.createNativeQuery(userDataSQL, UserDataEntity.class)
				.setParameter("userId", userId)
				.getSingleResult();
		UserDataDTO userDataDTO = super.map(userDataEntity, UserDataDTO.class);
		BackgroundDTO background = super.map(backgroundRepository.getById(userDataEntity.getBackgroundId()), BackgroundDTO.class);
		CharacterDTO characters = super.map(charactersRepository.getById(userDataEntity.getCharacterId()), CharacterDTO.class);
		userDataDTO.setBackground(background);
		userDataDTO.setCharacters(characters);
		return userDataDTO;
	}

	@Override
	public void saveSettingData(UserSettingForm data) {
		ActionUserDTO userDTO = ActionUserHolder.getActionUser();
		Users user = commonService.validateUser(userDTO);
		BigInteger count = (BigInteger) em.createNativeQuery("select count(ui.item_id) " +
						"from products p, item_products ip, user_items ui\n" +
						"where ui.user_id = :user_id\n" +
						"and ui.item_id = ip.item_id\n" +
						"and ip.product_id = p.id\n" +
						"and ip.item_id in :ids")
				.setParameter("user_id", user.getId())
				.setParameter("ids", List.of(data.getCharacterId(), data.getBackgroundId())).getSingleResult();
		Assert.isTrue(count.longValue() > 1, MsgUtil.getMessage("setting.invalid"));
		user.setMypageCharacterId(data.getCharacterId());
		user.setBackgroundId(data.getBackgroundId());
		usersRepository.save(user);
	}

	@Override
	public UserDataDTO getDataUserInMyPageWithDevice(String deviceId) {
		UserDataEntity userDataEntity = (UserDataEntity) em.createNativeQuery(userDataSQLDevice, UserDataEntity.class)
				.setParameter("deviceId", deviceId)
				.getSingleResult();
		UserDataDTO userDataDTO = super.map(userDataEntity, UserDataDTO.class);
		BackgroundDTO background = new BackgroundDTO();
		CharacterDTO characters = new CharacterDTO();
		if (!DataUtil.isNullOrEmpty(userDataEntity.getBackgroundId())) {
			background = super.map(backgroundRepository.getById(userDataEntity.getBackgroundId()), BackgroundDTO.class);
		}
		if (!DataUtil.isNullOrEmpty(userDataDTO.getCharacterId())) {
			characters = super.map(charactersRepository.getById(userDataEntity.getCharacterId()), CharacterDTO.class);
		}
		userDataDTO.setBackground(background);
		userDataDTO.setCharacters(characters);
		return userDataDTO;
	}
}
