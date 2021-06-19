package io.github.olgaak.service.api;

import io.github.olgaak.dto.StationDto;
import io.github.olgaak.entity.Station;
import io.github.olgaak.exception.ActionNotAllowedException;

import java.util.List;

public interface StationService {
    Station createNewStation(StationDto station) throws ActionNotAllowedException;

    List<StationDto> getAllStations();

    StationDto getStationById(long id);

    void deleteStation(long id);

    void editStation(Station station);
}
