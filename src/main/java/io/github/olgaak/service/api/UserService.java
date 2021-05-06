package io.github.olgaak.service.api;

import io.github.olgaak.dto.UserDto;
import io.github.olgaak.entity.User;
import io.github.olgaak.exception.UserAlreadyExistException;

public interface UserService {

    public User registerNewUserAccount(UserDto userDto) throws UserAlreadyExistException;

    public UserDto findByEmail(String email);
}
