package io.github.olgaak.util;

import io.github.olgaak.dto.StationDto;
import io.github.olgaak.entity.Station;

import java.util.stream.Collectors;

public class StationDtoConverter {

    public static StationDto convertStationEntityToDto(Station station) {
        StationDto stationDto = new StationDto();
        stationDto.setId(station.getId());
        stationDto.setName(station.getName());
        stationDto.setLatitude(station.getLatitude());
        stationDto.setLongitude(station.getLongitude());
        stationDto.setTimetableItems(station
                .getTimetableItems()
                .stream()
                .map(item -> TimetableDtoConverter
                        .convertTimetableItemEntityToDto(item))
                .sorted()
                .collect(Collectors.toList()));
        return stationDto;
    }


    public static StationDto convertStationEntityToDtoWithoutChildren(Station station) {
        StationDto stationDto = new StationDto();
        stationDto.setId(station.getId());
        stationDto.setName(station.getName());
        stationDto.setLatitude(station.getLatitude());
        stationDto.setLongitude(station.getLongitude());
        return stationDto;
    }

    public static Station convertStationDtoToEntity(StationDto stationDto) {
        Station station = new Station();
        station.setId(stationDto.getId());
        station.setName(stationDto.getName());
        station.setLatitude(stationDto.getLatitude());
        station.setLongitude(stationDto.getLongitude());
        return station;
    }

}
