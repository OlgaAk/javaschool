package io.github.olgaak.exception;

public class PassengerAlreadyExistsOnTrainException extends Exception{
    public PassengerAlreadyExistsOnTrainException(String errorMessage){
        super(errorMessage);
    }
}
