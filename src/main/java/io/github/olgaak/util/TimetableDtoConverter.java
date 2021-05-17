package io.github.olgaak.util;

import io.github.olgaak.dto.StationDto;
import io.github.olgaak.dto.TimetableItemDto;
import io.github.olgaak.entity.Route;
import io.github.olgaak.entity.Station;
import io.github.olgaak.entity.TimetableItem;
import io.github.olgaak.entity.Train;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class TimetableDtoConverter {

    public static TimetableItemDto convertTimetableItemEntityToDto(TimetableItem timetableItem) {
        TimetableItemDto timetableItemDto = new TimetableItemDto();
        timetableItemDto.setDepartureTimeAsDate(timetableItem.getDepartureTime());
        timetableItemDto.setDepartureDateAsDate(timetableItem.getDepartureDate());
        timetableItemDto.setFullDepartureDate(timetableItem.getFullDepartureDate());
        timetableItemDto.setId(timetableItem.getId());
        timetableItemDto.setStation(StationDtoConverter
                .convertStationEntityToDto(timetableItem.getStation()));
        return timetableItemDto;
    }

    public static TimetableItemDto convertTimetableItemEntityToDto(TimetableItem timetableItem, StationDto stationDto) {
        TimetableItemDto timetableItemDto = new TimetableItemDto();
        timetableItemDto.setDepartureTimeAsDate(timetableItem.getDepartureTime());
        timetableItemDto.setDepartureDateAsDate(timetableItem.getDepartureDate());
        timetableItemDto.setFullDepartureDate(timetableItem.getFullDepartureDate());
        timetableItemDto.setId(timetableItem.getId());
        timetableItemDto.setStation(stationDto);
        timetableItemDto.setTrainNumber(timetableItem.getTrain().getNumber());
        timetableItemDto.setStartTripStationName(RouteDtoConverter.getFirstStation(timetableItem.getRoute()).getStation().getName());
        timetableItemDto.setEndTripStationName(RouteDtoConverter.getLastStation(timetableItem.getRoute()).getStation().getName());
        return timetableItemDto;
    }

    public static TimetableItem convertTimetableItemDtoToEntity(TimetableItemDto timetableItemDto, Route route) {
        TimetableItem timetableItem = new TimetableItem();
        timetableItem.setId(timetableItemDto.getId());
        timetableItem.setRoute(route);
        timetableItem.setDepartureTime(parseTimeString(timetableItemDto.getDepartureTime()));
        timetableItem.setDepartureDate(parseDateString(timetableItemDto.getDepartureDate()));
        timetableItem.setStation(new Station(timetableItemDto.getStation().getId()));
        timetableItem.setTrain(new Train(timetableItemDto.getTrainId()));
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
