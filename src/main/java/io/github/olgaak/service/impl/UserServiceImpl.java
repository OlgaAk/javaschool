package io.github.olgaak.service.impl;

import io.github.olgaak.dao.api.UserDao;
import io.github.olgaak.dto.UserDto;
import io.github.olgaak.entity.User;
import io.github.olgaak.exception.UserAlreadyExistException;
import io.github.olgaak.service.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import org.modelmapper.ModelMapper;

import javax.transaction.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public User registerNewUserAccount(UserDto userDto) throws UserAlreadyExistException {
        if(emailExist(userDto.getEmail())){
            throw new UserAlreadyExistException("Account with this email adress already exists.");
        }
        User user = modelMapper.map(userDto, User.class);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDao.createNewUser(user);
        return user;
    }

    @Override
    public UserDto findByEmail(String email) {
        User user = userDao.findByEmail(email);
        if(user == null){
            throw new UsernameNotFoundException("User not found");
        }
        UserDto userDto = modelMapper.map(user, UserDto.class);
        return userDto;
    }

    private boolean emailExist(String email) {
        return userDao.findByEmail(email) != null;
    }

}
