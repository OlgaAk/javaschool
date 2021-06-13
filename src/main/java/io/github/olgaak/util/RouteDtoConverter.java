package io.github.olgaak.util;

import io.github.olgaak.dto.RouteDto;
import io.github.olgaak.dto.TimetableItemDto;
import io.github.olgaak.entity.Route;
import io.github.olgaak.entity.RoutePlan;
import io.github.olgaak.entity.TimetableItem;
import io.github.olgaak.entity.Train;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
        return routeDto;
    }


    public static Route convertRouteDtoToEntity(RouteDto routeDto) {
        Route route = new Route();
        route.setId(routeDto.getId());
        return route;
    }
}
