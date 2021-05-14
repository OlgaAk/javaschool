package io.github.olgaak.service.api;

import io.github.olgaak.dto.StationDto;
import io.github.olgaak.entity.Station;

import java.util.List;

public interface StationService {
    Station createNewStation(Station station);

    List<StationDto> getAllStations();

    void deleteStation(long id);

    void editStation(Station station);
}
