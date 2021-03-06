package io.github.olgaak.service.impl;

import io.github.olgaak.dao.api.StationDao;
import io.github.olgaak.dto.StationDto;
import io.github.olgaak.entity.Station;
import io.github.olgaak.exception.ActionNotAllowedException;
import io.github.olgaak.service.api.StationService;
import io.github.olgaak.util.StationDtoConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class StationServiceImpl implements StationService {

    private StationDao stationDao;

    @Autowired
    public StationServiceImpl(StationDao stationDao) {
        this.stationDao = stationDao;
    }

    public Station createNewStation(StationDto stationDto) throws ActionNotAllowedException {
        List<Station> stations = stationDao.getAllStations();
        Boolean present = stations.stream().filter(s -> s.getName().equals(stationDto.getName())).findAny().isPresent();
        if(present) throw new ActionNotAllowedException("Station already added");
        Station station = StationDtoConverter.convertStationDtoToEntity(stationDto);
        stationDao.createNewStation(station);
        return null;
    }

    public List<StationDto> getAllStations() {
        List<Station> stations = stationDao.getAllStations();
        return stations
                .stream()
                .map(station ->
                        StationDtoConverter.convertStationEntityToDtoWithoutChildren(station))
                .collect(Collectors.toList());
    }

    @Override
    public StationDto getStationById(long id) {
        Station station = stationDao.getStationById(id);
        return StationDtoConverter.convertStationEntityToDto(station);
    }

    @Override
    public void deleteStation(long id) throws ActionNotAllowedException {
        Station station = stationDao.getStationById(id);
        if(station.getTimetableItems().size()>0) throw new ActionNotAllowedException("Station has trains.");
        stationDao.deleteStation(id);
    }

    @Override
    public void editStation(Station station) {
        stationDao.editStation(station);
    }

}

