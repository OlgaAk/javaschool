package io.github.olgaak.util;

import io.github.olgaak.dto.RouteDto;
import io.github.olgaak.entity.Route;
import io.github.olgaak.entity.TimetableItem;

import java.util.Comparator;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class RouteDtoConverter {

    public static RouteDto convertRouteEntityToDto(Route route) {
        RouteDto routeDto = new RouteDto();
        TimetableItem firstStop = getFirstStation(route);
        TimetableItem lastStop = getLastStation(route);
        routeDto.setStartTripStation(firstStop.getStation().getName());
        routeDto.setStartTripTime(firstStop.getFullDepartureDate().toString());
        routeDto.setEndTripStation(lastStop.getStation().getName());
        routeDto.setEndTripTime(lastStop.getFullDepartureDate().toString());
        routeDto.setId(route.getId());
        routeDto.setTrain_id(route.getTrain().getId());
        long duration = getDurationMilli(lastStop, firstStop);
        routeDto.setSeats(route.getSeats().stream().map(seat -> SeatDtoConverter.convertSeatEntityToDto(seat)).collect(Collectors.toList()));
        routeDto.setTripDurationMilli(duration);
        routeDto.setTripDuration(getDuration(duration));
        return routeDto;
    }

    private static TimetableItem getFirstStation(Route route) {
        return route.getTimetableItems().stream()
                .min(Comparator.comparing(
                        timetableItem -> timetableItem.getFullDepartureDate()))
                .get();
    }

    private static TimetableItem getLastStation(Route route) {
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
}
