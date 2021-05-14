package io.github.olgaak.util;

import io.github.olgaak.dto.TicketDto;
import io.github.olgaak.entity.*;

import java.util.HashSet;
import java.util.Set;


public class TicketDtoConverter {

    public static TicketDto convertTicketEntityToDto(Ticket ticket) {
        TicketDto ticketDto = new TicketDto();
        ticketDto.setRouteId(ticket.getRoute().getId());
        ticketDto.setPrice(ticket.getPrice());
        ticketDto.setPassenger(PassengerDtoConverter.convertPassengerEntityToDto(ticket.getPassenger()));
        ticketDto.setSeat(SeatDtoConverter.convertSeatEntityToDto(ticket.getSeat()));
        return ticketDto;
    }

    public static Ticket convertTicketDtoToEntity(TicketDto ticketDto) {
        Ticket ticket = new Ticket();
        ticket.setRoute(new Route(ticketDto.getRouteId()));
        ticket.setPrice(ticketDto.getPrice());
        ticket.setPassenger(PassengerDtoConverter.convertPassengerDtoToEntity(ticketDto.getPassenger()));
        Seat seat = new Seat(ticketDto.getSeat().getNumber());
        seat.setId(ticketDto.getSeat().getId());
        Set<RouteSection> routeSectionList = new HashSet<>();
        RouteSection routeSection = new RouteSection(new Station(ticketDto.getTripStartStationId()),
                new Station(ticketDto.getTripEndStationId()));
        routeSectionList.add(routeSection);
        seat.setTakenRouteSections(routeSectionList);
        ticket.setSeat(seat);
        return ticket;
    }

}
