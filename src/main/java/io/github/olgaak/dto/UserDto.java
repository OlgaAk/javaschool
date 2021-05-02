package io.github.olgaak.dto;

import io.github.olgaak.entity.User;

import javax.validation.constraints.NotBlank;

public class UserDto {

    @NotBlank
    private String email;

    @NotBlank
    private String password;

    public UserDto(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

 }
