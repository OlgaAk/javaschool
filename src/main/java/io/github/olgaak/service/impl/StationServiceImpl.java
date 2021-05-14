package io.github.olgaak.service.impl;

import io.github.olgaak.dao.api.StationDao;
import io.github.olgaak.dto.StationDto;
import io.github.olgaak.entity.Station;
import io.github.olgaak.service.api.StationService;
import io.github.olgaak.util.StationDtoConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StationServiceImpl implements StationService {

    private StationDao stationDao;

    @Autowired
    public StationServiceImpl(StationDao stationDao) {
        this.stationDao = stationDao;
    }

    public Station createNewStation(Station station) {
        stationDao.createNewStation(station);
        return null;
    }

    public List<StationDto> getAllStations() {
        List<Station> stations = stationDao.getAllStations();
        return stations
                .stream()
                .map(station ->
                        StationDtoConverter.convertStationEntityToDto(station))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteStation(long id) {
        stationDao.deleteStation(id);
    }

    @Override
    public void editStation(Station station) {
        stationDao.editStation(station);
    }

}

