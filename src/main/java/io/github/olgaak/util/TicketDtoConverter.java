package io.github.olgaak.util;

import io.github.olgaak.dto.RouteDto;
import io.github.olgaak.dto.SeatDto;
import io.github.olgaak.dto.TicketDto;
import io.github.olgaak.entity.*;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class TicketDtoConverter {

    public static TicketDto convertTicketEntityToDto(Ticket ticket) {
        TicketDto ticketDto = new TicketDto();
        ticketDto.setRoute(RouteDtoConverter.convertRouteEntityToDto(ticket.getRoute()));
        ticketDto.setPrice(ticket.getPrice());
        ticketDto.setId(ticket.getId());

        ticketDto.setPassenger(PassengerDtoConverter.convertPassengerEntityToDto(ticket.getPassenger()));
        ticketDto.setStartStation(StationDtoConverter.convertStationEntityToDto(ticket.getStartStation()));
        ticketDto.setEndStation(StationDtoConverter.convertStationEntityToDto(ticket.getEndStation()));
        Date departureDate = getTicketDepartureDate(ticket, ticket.getEndStation().getId());
        Date arrivalDate = getTicketDepartureDate(ticket, ticket.getEndStation().getId());
        ticketDto.setArrivalTimeAsDate(arrivalDate);
        ticketDto.setDepartureTimeAsDAte(departureDate);
        ticketDto.setDepartureTime(DateTimeConverter.parseDateToString(departureDate));
        ticketDto.setArrivalTime(DateTimeConverter.parseDateToString(arrivalDate));
        ticketDto.setIsArchived(new Date().getTime()>arrivalDate.getTime());
        ticketDto.setSeat(SeatDtoConverter.convertSeatEntityToDto(ticket.getSeat(), ticketDto));
        return ticketDto;
    }

    // Used by conversion of Seat to SeatDto
    public static TicketDto convertTicketEntityToDto(Ticket ticket, RouteDto routeDto, SeatDto seatDto) {
        TicketDto ticketDto = new TicketDto();
        ticketDto.setRoute(routeDto);
        ticketDto.setPrice(ticket.getPrice());
        ticketDto.setId(ticket.getId());
        ticketDto.setPassenger(PassengerDtoConverter.convertPassengerEntityToDto(ticket.getPassenger()));
        ticketDto.setSeat(seatDto);
        ticketDto.setStartStation(StationDtoConverter.convertStationEntityToDto(ticket.getStartStation()));
        ticketDto.setEndStation(StationDtoConverter.convertStationEntityToDto(ticket.getEndStation()));
        return ticketDto;
    }

    public static Date getTicketDepartureDate(Ticket ticket, long id) {
        List<TimetableItem> timetableItemList = ticket
                .getRoute()
                .getTimetableItems()
                .stream()
                .filter(timetableItem ->
                        timetableItem.getStation().getId() == id)
                .collect(Collectors.toList());
        if (timetableItemList.size() != 1) {
            throw new IllegalStateException("Expected exactly one timetableItem but got " + timetableItemList);
        }
        TimetableItem timetableItem = timetableItemList.get(0);
        return timetableItem.getFullDepartureDate();
    }

    // Used by conversion of Route to RouteDto
    public static TicketDto convertTicketEntityToDto(Ticket ticket, RouteDto routeDto) {
        TicketDto ticketDto = new TicketDto();
        ticketDto.setRoute(routeDto);
        ticketDto.setPrice(ticket.getPrice());
        ticketDto.setId(ticket.getId());
        ticketDto.setPassenger(PassengerDtoConverter.convertPassengerEntityToDto(ticket.getPassenger()));
        ticketDto.setSeat(SeatDtoConverter.convertSeatEntityToDto(ticket.getSeat(), ticketDto));
        ticketDto.setSeatNumber(ticket.getSeat().getNumber());
        ticketDto.setStartStation(StationDtoConverter.convertStationEntityToDto(ticket.getStartStation()));
        ticketDto.setEndStation(StationDtoConverter.convertStationEntityToDto(ticket.getEndStation()));
        return ticketDto;
    }


    public static Ticket convertTicketDtoToEntity(TicketDto ticketDto) {
        Ticket ticket = new Ticket();
        ticket.setRoute(new Route(ticketDto.getRouteId()));
        ticket.setStartStation(new Station(ticketDto.getStartStation().getId()));
        ticket.setEndStation(new Station(ticketDto.getEndStation().getId()));
        ticket.setPrice(ticketDto.getPrice());
        ticket.setPassenger(PassengerDtoConverter.convertPassengerDtoToEntity(ticketDto.getPassenger()));
        ticket.setSeat(new Seat(ticketDto.getSeatId()));
        return ticket;
    }

}
