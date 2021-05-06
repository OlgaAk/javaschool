package io.github.olgaak.exception;

public class UserAlreadyExistException extends Exception{
    public UserAlreadyExistException(String message){
        super(message);
    }
}
