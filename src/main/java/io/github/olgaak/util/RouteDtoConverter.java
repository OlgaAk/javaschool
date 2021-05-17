package io.github.olgaak.util;

import io.github.olgaak.dto.RouteDto;
import io.github.olgaak.dto.SeatDto;
import io.github.olgaak.dto.TimetableItemDto;
import io.github.olgaak.entity.Route;
import io.github.olgaak.entity.TimetableItem;
import io.github.olgaak.entity.Train;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class RouteDtoConverter {

    public static RouteDto convertRouteEntityToDto(Route route) {
        RouteDto routeDto = new RouteDto();
        TimetableItem firstStop = getFirstStation(route);
        TimetableItem lastStop = getLastStation(route);
        routeDto.setStartTripStation(StationDtoConverter.convertStationEntityToDto(firstStop.getStation()));
        routeDto.setStartTripTime(firstStop.getFullDepartureDate().toString());
        routeDto.setEndTripStation(StationDtoConverter.convertStationEntityToDto(lastStop.getStation()));
        routeDto.setEndTripTime(lastStop.getFullDepartureDate().toString());
        routeDto.setId(route.getId());
        routeDto.setTrainId(route.getTrain().getId());
        long duration = getDurationMilli(lastStop, firstStop);
        routeDto.setTripDurationMilli(duration);
        routeDto.setTripDuration(getDuration(duration));
        routeDto.setSeats(route
                .getSeats()
                .stream()
                .map(seat -> SeatDtoConverter
                        .convertSeatEntityToDto(seat, routeDto)) //2nd parameter to avoid circular calls
                .collect(Collectors.toList()));
        List<TimetableItemDto> timetableItemDtoList = route
                .getTimetableItems()
                .stream()
                .map(timetableItem ->
                        TimetableDtoConverter
                                .convertTimetableItemEntityToDto(timetableItem))
                .sorted()
                .collect(Collectors.toList());
        routeDto.setTimetableItems(timetableItemDtoList);
        routeDto.setTrain(TrainDtoConverter.convertTrainEntityToDto(route.getTrain()));
        routeDto.setTickets(route
                .getTickets()
                .stream()
                .map(ticket -> TicketDtoConverter.convertTicketEntityToDto(ticket, routeDto))
                .collect(Collectors.toList()));
        return routeDto;
    }

    public static TimetableItem getFirstStation(Route route) {
        return route.getTimetableItems().stream()
                .min(Comparator.comparing(
                        timetableItem -> timetableItem.getFullDepartureDate()))
                .get();
    }

    public static TimetableItem getLastStation(Route route) {
        return route.getTimetableItems().stream()
                .max(Comparator.comparing(
                        timetableItem -> timetableItem.getFullDepartureDate()))
                .get();
    }

    private static long getDurationMilli(TimetableItem lastStop, TimetableItem firstStop) {
        return lastStop.getFullDepartureDate().getTime() - firstStop.getFullDepartureDate().getTime();
    }

    private static String getDuration(long duration) {
        long diffInMinutes = TimeUnit.MINUTES.convert(duration, TimeUnit.MILLISECONDS);
        long hours = diffInMinutes / 60;
        long minutes = diffInMinutes % 60;
        return hours + ":" + (minutes < 10 ? "0" : "") + minutes;
    }

    public static Route convertRouteDtoToEntity(RouteDto routeDto) {
        Route route = new Route();
        route.setId(routeDto.getId());
        route.setTrain(new Train(routeDto.getTrainId()));
        List<TimetableItem> timetableItems;
        timetableItems = routeDto.getTimetableItems()
                .stream()
                .map(timetableItemDto -> {
                    timetableItemDto.setTrainId(routeDto.getTrainId());
                    return TimetableDtoConverter.convertTimetableItemDtoToEntity(timetableItemDto, route);
                })
                .collect(Collectors.toList());
        ;
        route.setTimetableItems(new HashSet<>(timetableItems));
        return route;
    }
}
