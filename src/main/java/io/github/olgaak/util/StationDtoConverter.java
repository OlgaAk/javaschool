package io.github.olgaak.util;

import io.github.olgaak.dto.StationDto;
import io.github.olgaak.entity.Station;

import java.util.stream.Collectors;

public class StationDtoConverter {

    public static StationDto convertStationEntityToDto(Station station) {
        StationDto stationDto = new StationDto();
        stationDto.setId(station.getId());
        stationDto.setName(station.getName());
//        stationDto.setTimetableItems(station
//                .getTimetableItems()
//                .stream()
//                .map(item -> TimetableDtoConverter
//                        .convertTimetableItemEntityToDto(item, stationDto))
//                .sorted()
//                .collect(Collectors.toList()));
        return stationDto;
    }

    public static Station convertStationDtoToEntity(StationDto stationDto) {
        Station station = new Station();
        station.setId(stationDto.getId());
        station.setName(stationDto.getName());
        return station;
    }

}
