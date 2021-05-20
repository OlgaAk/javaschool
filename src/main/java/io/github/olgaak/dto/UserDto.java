package io.github.olgaak.dto;

import javax.validation.constraints.*;
import java.util.Date;


public class UserDto {

    @Email
    @NotBlank(message = "Email is required")
    private String email;

    @NotBlank(message = "Password is required")
    @Size(min = 4, message = "Password should have at least 4 characters")
    private String password;

    @NotBlank(message = "Firstname is required")
    private String firstName;

    @NotBlank(message = "Lastname is required")
    private String lastName;

    public UserDto() { }

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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

}
