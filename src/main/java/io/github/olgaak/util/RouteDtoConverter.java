package io.github.olgaak.util;

import io.github.olgaak.dto.RouteDto;
import io.github.olgaak.entity.Route;

import java.util.stream.Collectors;


public class RouteDtoConverter {

    public static RouteDto convertRouteEntityToDto(Route route) {
        RouteDto routeDto = new RouteDto();
        routeDto.setId(route.getId());
        routeDto.setSeats(route
                .getSeats()
                .stream()
                .map(seat -> SeatDtoConverter
                        .convertSeatEntityToDto(seat, routeDto)) //2nd parameter to avoid circular calls
                .collect(Collectors.toList()));
        routeDto.setTickets(route
                .getTickets()
                .stream()
                .map(ticket -> TicketDtoConverter.convertTicketEntityToDto(ticket, routeDto))
                .collect(Collectors.toList()));
        routeDto.setDepartureDate(route.getDepartureDate());
        return routeDto;
    }


    public static Route convertRouteDtoToEntity(RouteDto routeDto) {
        Route route = new Route();
        route.setId(routeDto.getId());
        return route;
    }
}
