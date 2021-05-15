package io.github.olgaak.util;

import io.github.olgaak.dto.PassengerDto;
import io.github.olgaak.entity.Passenger;
import io.github.olgaak.entity.User;

public class PassengerDtoConverter {

    public static PassengerDto convertPassengerEntityToDto(Passenger passenger) {
        PassengerDto passengerDto = new PassengerDto();
        passengerDto.setPassportNumber(passenger.getPassportNumber());
        passengerDto.setId(passenger.getId());
        passengerDto.setDateOfBirth(passenger.getDateOfBirth());
        passengerDto.setFirstName(passenger.getFirstName());
        passengerDto.setLastName(passenger.getLastName());
        passengerDto.setUserId(passenger.getUser().getId());
        return passengerDto;
    }

    public static Passenger convertPassengerDtoToEntity(PassengerDto passengerDto) {
        Passenger passenger = new Passenger();
        passenger.setPassportNumber(passengerDto.getPassportNumber());
        passenger.setDateOfBirth(passengerDto.getDateOfBirth());
        passenger.setFirstName(passengerDto.getFirstName());
        passenger.setLastName(passengerDto.getLastName());
        passenger.setId(passengerDto.getId());
        passenger.setUser(new User(passengerDto.getUserId()));
        return passenger;
    }
}
