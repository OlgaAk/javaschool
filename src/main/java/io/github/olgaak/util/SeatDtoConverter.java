package io.github.olgaak.util;

import io.github.olgaak.dto.SeatDto;
import io.github.olgaak.entity.Seat;

import java.util.stream.Collectors;

public class SeatDtoConverter {

    public static SeatDto convertSeatEntityToDto(Seat seat) {
        SeatDto seatDto = new SeatDto();
        seatDto.setNumber(seat.getNumber());
        seatDto.setId(seat.getId());
        seatDto.setTakenRouteSections(seat
                .getTakenRouteSections()
                .stream()
                .map(routeSection -> RouteSectionDtoConverter
                        .convertRouteSectionEntityToDto(routeSection))
                .collect(Collectors.toList()));
        return seatDto;
    }

}
