package io.github.olgaak.util;

import io.github.olgaak.dto.StationDto;
import io.github.olgaak.entity.Station;

public class StationDtoConverter {

    public static StationDto convertStationEntityToDto(Station station){
        StationDto stationDto = new StationDto();
        stationDto.setId(station.getId());
        stationDto.setName(station.getName());
        return stationDto;
    }

}
