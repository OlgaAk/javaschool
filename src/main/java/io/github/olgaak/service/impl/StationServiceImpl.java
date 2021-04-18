package io.github.olgaak.service.impl;

import io.github.olgaak.dao.StationDao;
import io.github.olgaak.entity.Station;
import io.github.olgaak.service.api.StationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StationServiceImpl implements StationService {

    private StationDao stationDao;

    @Autowired
    public StationServiceImpl(StationDao stationDao){
        this.stationDao = stationDao;
    }

    public Station createNewStation(Station station) {
        stationDao.createNewStation(station);
        return null;
    }

    public List<Station> getAllStations() {
        return stationDao.getAllStations();
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

