package io.github.olgaak.service.impl;

import io.github.olgaak.dao.api.UserDao;
import io.github.olgaak.dto.UserDto;
import io.github.olgaak.entity.User;
import io.github.olgaak.exceptions.UserAlreadyExistException;
import io.github.olgaak.service.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import org.modelmapper.ModelMapper;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User registerNewUserAccount(UserDto userDto) throws UserAlreadyExistException {
        if(emailExist(userDto.getEmail())){
            throw new UserAlreadyExistException("Account with this email adress already exists.");
        }
        ModelMapper modelMapper = new ModelMapper();
        User user = modelMapper.map(userDto, User.class);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDao.createNewUser(user);
        return user;
    }
    private boolean emailExist(String email) {
        return userDao.findByEmail(email) != null;
    }

}