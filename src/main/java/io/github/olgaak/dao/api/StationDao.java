package io.github.olgaak.dao.api;

import io.github.olgaak.entity.Station;

import java.util.List;

public interface StationDao {
    void createNewStation(Station station);

    List<Station> getAllStations();

    void deleteStation(long id);

    void editStation(Station station);
}
