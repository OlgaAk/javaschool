package io.github.olgaak.service.impl;

import io.github.olgaak.dao.api.TrainDao;
import io.github.olgaak.dto.RouteDto;
import io.github.olgaak.dto.StationDto;
import io.github.olgaak.dto.TrainDto;
import io.github.olgaak.entity.Route;
import io.github.olgaak.entity.Train;
import io.github.olgaak.service.api.TrainService;
import io.github.olgaak.util.RouteDtoConverter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TrainServiceImpl implements TrainService {

    private TrainDao trainDao;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    public TrainServiceImpl(TrainDao trainDao){
        this.trainDao = trainDao;
    }

    public Train createNewTrain(Train train) {
        trainDao.createNewTrain(train);
        return null;
    }

    public List<TrainDto> getAllTrains() {
        List<Train> trains = trainDao.getAllTrains();
        return trains.stream().map(train-> {
            TrainDto trainDto = modelMapper.map(train, TrainDto.class);
            List<String> stations = new ArrayList<>();
            for(Route route : train.getRoutes()){
                RouteDto routeDto = RouteDtoConverter.convertRouteEntityToDto(route);
                stations.add(routeDto.getStartTripStation().getName());
                stations.add(routeDto.getEndTripStation().getName());
            }
            trainDto.setStations(stations);
            return trainDto;
        }).collect(Collectors.toList());

    }

    public TrainDto getTrainById(long id){
        Train train = trainDao.getTrainById(id);
        return modelMapper.map(train, TrainDto.class);
    }

    @Override
    public void deleteTrain(long id) {
        trainDao.deleteTrain(id);
    }

    @Override
    public void editTrain(Train train) {
        trainDao.editTrain(train);
    }

}
