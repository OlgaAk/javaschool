package io.github.olgaak.util;

import io.github.olgaak.dto.TicketDto;
import io.github.olgaak.entity.*;

public class TicketDtoConverter {

    public static TicketDto convertTicketEntityToDto(Ticket ticket) {
        TicketDto ticketDto = new TicketDto();
        ticketDto.setRouteId(ticket.getRoute().getId());
        ticketDto.setPrice(ticket.getPrice());
        ticketDto.setId(ticket.getId());
        ticketDto.setRoute(RouteDtoConverter.convertRouteEntityToDto(ticket.getRoute()));
        ticketDto.setPassenger(PassengerDtoConverter.convertPassengerEntityToDto(ticket.getPassenger()));
        ticketDto.setSeat(SeatDtoConverter.convertSeatEntityToDto(ticket.getSeat()));
        ticketDto.setStartStation(StationDtoConverter.convertStationEntityToDto(ticket.getStartStation()));
        ticketDto.setEndStation(StationDtoConverter.convertStationEntityToDto(ticket.getEndStation()));
        return ticketDto;
    }

    public static Ticket convertTicketDtoToEntity(TicketDto ticketDto) {
        Ticket ticket = new Ticket();
        ticket.setRoute(new Route(ticketDto.getRoute().getId()));
        ticket.setStartStation(new Station(ticketDto.getStartStation().getId()));
        ticket.setEndStation(new Station(ticketDto.getEndStation().getId()));
        ticket.setPrice(ticketDto.getPrice());
        ticket.setPassenger(PassengerDtoConverter.convertPassengerDtoToEntity(ticketDto.getPassenger()));
        Seat seat = new Seat(ticketDto.getSeat().getNumber());
        seat.setId(ticketDto.getSeat().getId());

        //  RouteSection routeSection = RouteSectionDtoConverter.convertRouteSectionDtoToEntity(ticketDto.getRouteSection());
//        Set<RouteSection> routeSectionList = new HashSet<>();
//        routeSectionList.add(routeSection);
//        seat.setTakenRouteSections(routeSectionList); //todo  seat can be assigned to several tickets
        ticket.setSeat(seat);
        return ticket;
    }

}
