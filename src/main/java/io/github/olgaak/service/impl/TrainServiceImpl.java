package io.github.olgaak.service.impl;

import io.github.olgaak.dao.api.TrainDao;
import io.github.olgaak.dto.RouteDto;
import io.github.olgaak.dto.RoutePlanDto;
import io.github.olgaak.dto.StationDto;
import io.github.olgaak.dto.TrainDto;
import io.github.olgaak.entity.Route;
import io.github.olgaak.entity.RoutePlan;
import io.github.olgaak.entity.Train;
import io.github.olgaak.service.api.TrainService;
import io.github.olgaak.util.RouteDtoConverter;
import io.github.olgaak.util.RoutePlanDtoConverter;
import io.github.olgaak.util.TrainDtoConverter;
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
    public TrainServiceImpl(TrainDao trainDao) {
        this.trainDao = trainDao;
    }

    public void createNewTrain(TrainDto trainDto) {
        Train train = TrainDtoConverter.convertTrainDtoToEntity(trainDto);
        trainDao.createNewTrain(train);
    }

    public List<TrainDto> getAllTrains() {
        List<Train> trains = trainDao.getAllTrains();
        return trains.stream().map(train -> {
            TrainDto trainDto = TrainDtoConverter.convertTrainEntityToDto(train);
            List<String> stations = new ArrayList<>();
            RoutePlanDto routePlanDto = RoutePlanDtoConverter.convertRoutePlanEntityToDto(train.getRoutePlan());
            stations.add(routePlanDto.getStartTripStation().getName());
            stations.add(routePlanDto.getEndTripStation().getName());
            trainDto.setStations(stations);
            return trainDto;
        }).collect(Collectors.toList());
    }

    public TrainDto getTrainById(long id) {
        Train train = trainDao.getTrainById(id);
        return TrainDtoConverter.convertTrainEntityToDto(train);
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
