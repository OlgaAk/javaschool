package io.github.olgaak.util;

import io.github.olgaak.dto.RouteDto;
import io.github.olgaak.dto.SeatDto;
import io.github.olgaak.dto.TicketDto;
import io.github.olgaak.entity.Seat;

import java.util.stream.Collectors;

public class SeatDtoConverter {

    public static SeatDto convertSeatEntityToDto(Seat seat) {
        SeatDto seatDto = new SeatDto();
        seatDto.setNumber(seat.getNumber());
        seatDto.setId(seat.getId());
        seatDto.setTickets(seat
                .getTickets()
                .stream()
                .map(ticket -> TicketDtoConverter
                        .convertTicketEntityToDto(ticket))
                .collect(Collectors.toList()));
        return seatDto;
    }

    public static SeatDto convertSeatEntityToDto(Seat seat, TicketDto ticketDto) {
        SeatDto seatDto = new SeatDto();
        seatDto.setNumber(seat.getNumber());
        seatDto.setId(seat.getId());
//        seatDto.setTickets(seat
//                .getTickets()
//                .stream()
//                .map(ticket -> TicketDtoConverter
//                        .convertTicketEntityToDto(ticket))
//                .collect(Collectors.toList()));
        return seatDto;
    }

    //additional parameter Route to pass to Ticket in order to avoid circular method calls and stackoverflow
    public static SeatDto convertSeatEntityToDto(Seat seat, RouteDto routeDto) {
        SeatDto seatDto = new SeatDto();
        seatDto.setNumber(seat.getNumber());
        seatDto.setId(seat.getId());
        seatDto.setTickets(seat
                .getTickets()
                .stream()
                .map(ticket -> TicketDtoConverter
                        .convertTicketEntityToDto(ticket, routeDto, seatDto))
                .collect(Collectors.toList()));
        return seatDto;
    }

}
