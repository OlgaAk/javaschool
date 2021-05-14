package io.github.olgaak.util;

import io.github.olgaak.dto.SeatDto;
import io.github.olgaak.entity.Seat;

public class SeatDtoConverter {

    public static SeatDto convertSeatEntityToDto(Seat seat){
        SeatDto seatDto = new SeatDto();
        seatDto.setNumber(seat.getNumber());
        return seatDto;
    }

}
