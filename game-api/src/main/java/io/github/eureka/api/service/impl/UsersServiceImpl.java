package io.github.eureka.api.service.impl;

import io.github.eureka.api.common.Constant;
import io.github.eureka.api.common.MsgUtil;
import io.github.eureka.api.model.Users;
import io.github.eureka.api.model.dto.ChangePasswordDTO;
import io.github.eureka.api.repo.UsersRepository;
import io.github.eureka.api.securities.PBKDF2Encoder;
import io.github.eureka.api.service.UsersService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UsersServiceImpl implements UsersService {
    private final UsersRepository usersRepository;
    private final PBKDF2Encoder passwordEncoder;

    @Override
    public Users createUser(Users users) {
        Optional<Users> existingUserName = usersRepository.findByUsernameAndStatusIn(users.getUsername(), Arrays.asList(Constant.STATUS.ANONYMOUS, Constant.STATUS.REGITERED));
        Assert.isNull(existingUserName, MsgUtil.getMessage("username.exist"));
        Optional<Users> existingName = usersRepository.findByNameAndStatusIn(users.getName(), Arrays.asList(Constant.STATUS.ANONYMOUS, Constant.STATUS.REGITERED));
        Assert.isNull(existingName, MsgUtil.getMessage("name.exist"));
        Date birthDay = new Date(Calendar.getInstance().getTimeInMillis());
        users.setAge(0);
        users.setStatus(Constant.STATUS.ANONYMOUS);
        users.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        users.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        users.setBirthday(birthDay);
        users = usersRepository.save(users);
        return users;
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
}
