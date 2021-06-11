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
        TimetableItem firstStop = getFirstStation(route.getRoutePlan());
        TimetableItem lastStop = getLastStation(route.getRoutePlan());
        routeDto.setStartTripStation(StationDtoConverter.convertStationEntityToDto(firstStop.getStation()));
        routeDto.setStartTripTime(firstStop.getFullDepartureDate(route.getDepartureDate()).toString());
        routeDto.setEndTripStation(StationDtoConverter.convertStationEntityToDto(lastStop.getStation()));
        routeDto.setEndTripTime(lastStop.getFullDepartureDate(route.getDepartureDate()).toString());
        routeDto.setStartTripTimeHours(DateTimeConverter.parseDateToString(firstStop.getFullDepartureDate(route.getDepartureDate()), "HH:mm"));
        routeDto.setEndTripTimeHours(DateTimeConverter.parseDateToString(lastStop.getFullDepartureDate(route.getDepartureDate()), "HH:mm"));
        routeDto.setId(route.getId());
        routeDto.setTrainId(route.getTrain().getId());
        long duration = getDurationMilli(route, lastStop);
        routeDto.setTripDurationMilli(duration);
        routeDto.setTripDuration(getDuration(duration));
        routeDto.setSeats(route
                .getSeats()
                .stream()
                .map(seat -> SeatDtoConverter
                        .convertSeatEntityToDto(seat, routeDto)) //2nd parameter to avoid circular calls
                .collect(Collectors.toList()));
        List<TimetableItemDto> timetableItemDtoList = route
                .getRoutePlan()
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

    public static TimetableItem getFirstStation(RoutePlan routePlan) {
        return routePlan.getTimetableItems().stream()
                .min(Comparator.comparing(
                        timetableItem -> timetableItem.getOrder()))
                .get();
    }

    public static TimetableItem getLastStation(RoutePlan routePlan) {
        return routePlan.getTimetableItems().stream()
                .max(Comparator.comparing(
                        timetableItem -> timetableItem.getOrder()))
                .get();
    }

    private static long getDurationMilli(Route route, TimetableItem lastStop) {
        AtomicLong duration = new AtomicLong();
        route.getRoutePlan().getTimetableItems().stream().forEach(item -> {
            if (item.getId() != lastStop.getId()) duration.addAndGet(item.getDepartureTime().getTime());
            else duration.addAndGet(item.getArrivalTime().getTime());
        });

        return duration.longValue();
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
        List<TimetableItem> timetableItems =
        IntStream.range(0, routeDto.getTimetableItems().size())
                .mapToObj(i -> TimetableDtoConverter.convertTimetableItemDtoToEntity(routeDto.getTimetableItems().get(i), route, i))
                .collect(Collectors.toList());
        route.getRoutePlan().setTimetableItems(new HashSet<>(timetableItems));
        return route;
    }
}
