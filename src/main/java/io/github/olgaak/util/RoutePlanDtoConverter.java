package io.github.olgaak.util;

import io.github.olgaak.dto.RoutePlanDto;
import io.github.olgaak.dto.TimetableItemDto;
import io.github.olgaak.entity.*;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class RoutePlanDtoConverter {

    public static RoutePlanDto convertRoutePlanEntityToDto(RoutePlan routePlan) {
        RoutePlanDto routePlanDto = new RoutePlanDto();
        TimetableItem firstStop = getFirstStation(routePlan);
        TimetableItem lastStop = getLastStation(routePlan);
        routePlanDto.setStartTripStation(StationDtoConverter.convertStationEntityToDtoWithoutChildren(firstStop.getStation()));
        routePlanDto.setStartTripTime(firstStop.getDepartureTime().toString());
        routePlanDto.setEndTripStation(StationDtoConverter.convertStationEntityToDtoWithoutChildren(lastStop.getStation()));
        routePlanDto.setEndTripTime(lastStop.getArrivalTime().toString());
        routePlanDto.setStartTripTimeHours(DateTimeConverter.parseDateToString(firstStop.getDepartureTime(), "HH:mm"));
        routePlanDto.setEndTripTimeHours(DateTimeConverter.parseDateToString(lastStop.getArrivalTime(), "HH:mm"));
        routePlanDto.setId(routePlan.getId());
        long duration = getDurationMilli(routePlan, lastStop);
        routePlanDto.setTripDurationMilli(duration);
        routePlanDto.setTripDuration(getDuration(duration));
        List<TimetableItemDto> timetableItemDtoList = routePlan
                .getTimetableItems()
                .stream()
                .map(timetableItem ->
                        TimetableDtoConverter
                                .convertTimetableItemEntityToDto(timetableItem))
                .sorted()
                .collect(Collectors.toList());
        routePlanDto.setTimetableItems(timetableItemDtoList);
        List<Integer> weekdays = routePlan.getWeekdays().stream().distinct().map(weekday -> weekday.ordinal()).collect(Collectors.toList());
        routePlanDto.setWeekdays(weekdays);
        routePlanDto.setRoutes(routePlan.getRoutes().stream().map(route -> RouteDtoConverter.convertRouteEntityToDto(route)).sorted().collect(Collectors.toList()));
        return routePlanDto;
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

    private static long getDurationMilli(RoutePlan routePlan, TimetableItem lastStop) {
        AtomicLong duration = new AtomicLong();
        routePlan.getTimetableItems().stream().forEach(item -> {
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

    public static RoutePlan convertRoutePlanDtoToEntity(RoutePlanDto routePlanDto) {
        RoutePlan routePlan = new RoutePlan();
        routePlan.setId(routePlanDto.getId());
        List<TimetableItemDto> tList = routePlanDto.getTimetableItems();
        if (tList == null || tList.size() < 2) {
            throw new IllegalArgumentException("Timetable items size should be > 2 ");
        }
        List<TimetableItem> timetableItems =
                IntStream.range(0, tList.size())
                        .mapToObj(i -> TimetableDtoConverter.convertTimetableItemDtoToEntity(tList.get(i), routePlan, i))
                        .collect(Collectors.toList());
        routePlan.setTimetableItems(new HashSet<>(timetableItems));
        routePlan.setStartTripStation(new Station(tList.get(0).getStationId()));
        routePlan.setEndTripStation(new Station(tList.get(timetableItems.size() - 1).getStationId()));
        List<Weekday> weekdays = routePlanDto.getWeekdays().stream().map(weekday -> Weekday.values()[weekday]).collect(Collectors.toList());
        routePlan.setWeekdays(weekdays);
        return routePlan;
    }
}
