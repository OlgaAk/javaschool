package io.github.olgaak.util;

import io.github.olgaak.dto.TimetableItemDto;
import io.github.olgaak.entity.Route;
import io.github.olgaak.entity.Station;
import io.github.olgaak.entity.TimetableItem;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class TimetableDtoConverter {

    public static TimetableItemDto convertTimetableItemEntityToDto(TimetableItem timetableItem) {
        TimetableItemDto timetableItemDto = new TimetableItemDto();
        timetableItemDto.setDepartureTimeAsDate(timetableItem.getDepartureTime());
        timetableItemDto.setArrivalTimeAsDate(timetableItem.getArrivalTime());
        timetableItemDto.setStationId(timetableItem.getStation().getId());
        timetableItemDto.setStationName(timetableItem.getStation().getName());
        timetableItemDto.setStartTripStationName(RouteDtoConverter.getFirstStation(timetableItem.getRoutePlan()).getStation().getName());
        timetableItemDto.setEndTripStationName(RouteDtoConverter.getLastStation(timetableItem.getRoutePlan()).getStation().getName());
        timetableItemDto.setId(timetableItem.getId());
        timetableItemDto.setStationId(timetableItem.getStation().getId());
        timetableItemDto.setStationName(timetableItem.getStation().getName());
        timetableItemDto.setOrder(timetableItem.getOrder());
        return timetableItemDto;
    }


    public static TimetableItem convertTimetableItemDtoToEntity(TimetableItemDto timetableItemDto, Route route, int order) {
        TimetableItem timetableItem = new TimetableItem();
        timetableItem.setId(timetableItemDto.getId());
        timetableItem.setRoutePlan(route.getRoutePlan());
        timetableItem.setOrder(order);
        timetableItem.setDepartureTime(parseTimeString(timetableItemDto.getDepartureTime()));
        timetableItem.setArrivalTime(parseDateString(timetableItemDto.getArrivalTime()));
        timetableItem.setStation(new Station(timetableItemDto.getStationId()));
        return timetableItem;
    }

    public static Date parseTimeString(String time) {
        DateFormat format = new SimpleDateFormat("HH:mm");
        Date date = null;
        try {
            date = format.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public static Date parseDateString(String dateString) {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = format.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
}
