package io.github.eureka.api.service.impl;

import com.github.javafaker.Faker;
import io.github.eureka.api.common.Constant;
import io.github.eureka.api.common.MsgUtil;
import io.github.eureka.api.config.ActionUserHolder;
import io.github.eureka.api.model.Users;
import io.github.eureka.api.model.dto.*;
import io.github.eureka.api.model.entity.UserDataEntity;
import io.github.eureka.api.repo.BackgroundRepository;
import io.github.eureka.api.repo.CharactersRepository;
import io.github.eureka.api.repo.UsersRepository;
import io.github.eureka.api.securities.PBKDF2Encoder;
import io.github.eureka.api.service.BaseService;
import io.github.eureka.api.service.CommonService;
import io.github.eureka.api.service.ItemService;
import io.github.eureka.api.service.UsersService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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
    private static final String userDataSQL = "select u.username, u.name, u.age, u.character_id, u.background_id,\n" +
            "uw.jewel_number, uw.jewel_bonus_number, uw.coin_number, stamina_number, heart, heart_30, heart_60\n" +
            "from users u\n" +
            "left join(\n" +
            "select uw.user_id, sum(IF(w.wallet_type = 'JEWEL', uw.number, 0)) jewel_number,\n" +
            "    sum(IF(w.wallet_type = 'JEWEL_BONUS', uw.number, 0)) jewel_bonus_number,\n" +
            "    sum(IF(w.wallet_type = 'COIN', uw.number, 0)) coin_number\n" +
            "from user_wallets uw left join wallets w\n" +
            "    on uw.wallet_id = w.id and w.wallet_type IN ('JEWEL', 'COIN', 'JEWEL_BONUS')\n" +
            "group by uw.user_id) uw ON u.id = uw.user_id\n" +
            "left join(select ui.user_id, sum( IF(ui.item_type = 'STAMINA', ui.number, 0) ) stamina_number,\n" +
            "                 sum( IF(ui.item_type = 'HEART', ui.number, 0) ) heart,\n" +
            "                 sum( IF(ui.item_type = 'HEART30', ui.number, 0) ) heart_30,\n" +
            "                 sum( IF(ui.item_type = 'HEART60', ui.number, 0) ) heart_60\n" +
            "                 from user_items ui where ui.item_type IN ('STAMINA', 'HEART', 'HEART30', 'HEART60')\n" +
            "    group by ui.user_id) ui ON u.id = ui.user_id\n" +
            "where u.id = :userId";

    @Override
    public Users createUser(CreateUserDTO users) {
        //Auto gen username
        Faker faker = new Faker();
        String username = faker.superhero().prefix()+faker.name().firstName()+faker.address().buildingNumber();
        Optional<Users> existingUserName = usersRepository.findByUsernameAndStatusIn(username, Arrays.asList(Constant.STATUS.ANONYMOUS, Constant.STATUS.REGITERED));
        Assert.isTrue(existingUserName.isEmpty(), MsgUtil.getMessage("username.exist"));
        Optional<Users> existingName = usersRepository.findByNameAndStatusIn(users.getName(), Arrays.asList(Constant.STATUS.ANONYMOUS, Constant.STATUS.REGITERED));
        Assert.isTrue(existingName.isEmpty(), MsgUtil.getMessage("name.exist"));
        Users newUser = new Users();
        newUser.setUsername(username);
        newUser.setDeviceId(users.getDeviceId());
        newUser.setBirthday(users.getBirthday());
        newUser.setName(users.getName());
        newUser.setAge(new Date(Calendar.getInstance().getTimeInMillis()), users.getBirthday());
        newUser.setStatus(Constant.STATUS.ANONYMOUS);
        newUser.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        newUser.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        newUser = usersRepository.save(newUser);
        return newUser;
    }

    @Override
    public Users getUserById(Long id) {
        Users user = usersRepository.findByIdAndStatusIn(id, Arrays.asList(Constant.STATUS.ANONYMOUS, Constant.STATUS.REGITERED)).orElseThrow (
                () -> new IllegalArgumentException(MsgUtil.getMessage("user.info.null"))
        );
        return user;
    }

    @Override
    public Users updateUser(Users users) {
        Users existingId = usersRepository.getById(users.getId());
        Assert.notNull(existingId, MsgUtil.getMessage("user.info.null"));
        Optional<Users> existingUser = usersRepository.findByUsernameAndStatusIn(users.getUsername(), Arrays.asList(Constant.STATUS.ANONYMOUS, Constant.STATUS.REGITERED));
        if(existingUser.isPresent()) {
            if (!existingUser.get().getId().equals(users.getId()))
                throw new IllegalArgumentException(MsgUtil.getMessage("userName.exist"));
        }
        Optional<Users> existingName = usersRepository.findByNameAndStatusIn(users.getName(), Arrays.asList(Constant.STATUS.ANONYMOUS, Constant.STATUS.REGITERED));
        if(existingName.isPresent()) {
            if (!existingName.get().getId().equals(users.getId()))
                throw new IllegalArgumentException(MsgUtil.getMessage("name.exist"));
        }
        users.setAge(new Date(Calendar.getInstance().getTimeInMillis()), users.getBirthday());
        users.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        usersRepository.save(users);
        return users;
    }

    @Override
    public void updateUserPassword(ChangePasswordDTO users) {
        if(!users.getPassword().equals(users.getConfirmPassword())){
            throw new IllegalArgumentException(MsgUtil.getMessage("password.notmatch"));
        }
        Users existingId = usersRepository.getById(users.getId());
        Assert.notNull(existingId, MsgUtil.getMessage("user.info.null"));
        existingId.setEncryptedPassword(passwordEncoder.encode(users.getPassword()));
        existingId.setStatus(Constant.STATUS.REGITERED);
        usersRepository.save(existingId);
    }

    @Override
    public List<Users> getAllUser() {
        return usersRepository.findAllByStatusIn(Arrays.asList(Constant.STATUS.ANONYMOUS, Constant.STATUS.REGITERED));
    }

    @Override
    public UserDataDTO getDataUserInMyPage(Long userId) {
        UserDataEntity userDataEntity = (UserDataEntity) em.createNativeQuery(userDataSQL , UserDataEntity.class)
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
    public void saveSettingData(UserSettingDTO data) {
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
        user.setCharacterId(data.getCharacterId());
        user.setBackgroundId(data.getBackgroundId());
        usersRepository.save(user);
    }
}
