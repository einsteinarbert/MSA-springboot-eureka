package io.github.eureka.api.service.impl;

import io.github.eureka.api.model.Users;
import io.github.eureka.api.repo.UsersRepository;
import io.github.eureka.api.service.UsersService;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.sql.Date;
import java.util.Calendar;

@Service
public class UsersServiceImpl implements UsersService {
    private final UsersRepository usersRepository;

    public UsersServiceImpl(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public Users createUser(Users users) {
        Date birthDay = new Date(Calendar.getInstance().getTimeInMillis());
        users.setAge("0");
        users.setStatus(0);
        users.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        users.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        users.setBirthday(birthDay);
        users = usersRepository.save(users);
        return users;
    }

    @Override
    public Users getUserById(Long id) {
        return usersRepository.getById(id);
    }

    @Override
    public Users updateUser(Users users) {
//        users.setAge(new Date(Calendar.getInstance().getTimeInMillis()) - users.getBirthday());
        users.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        usersRepository.save(users);
        return users;
    }
}
