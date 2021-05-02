package io.github.olgaak.service.impl;

import io.github.olgaak.dao.api.UserDao;
import io.github.olgaak.dto.UserDto;
import io.github.olgaak.entity.User;
import io.github.olgaak.exceptions.UserAlreadyExistException;
import io.github.olgaak.service.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public User registerNewUserAccount(UserDto userDto) throws UserAlreadyExistException {
        if(emailExist(userDto.getEmail())){
            throw new UserAlreadyExistException("Account with this email adress already exists.");
        }
        User user = userDto._toConvertUserEntity();
        userDao.createNewUser(user);
        return user;
    }
    private boolean emailExist(String email) {
        return userDao.findByEmail(email) != null;
    }

}
