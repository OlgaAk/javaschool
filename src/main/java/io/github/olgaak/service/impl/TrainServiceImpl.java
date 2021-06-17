package io.github.olgaak.service.impl;

import io.github.olgaak.dao.api.TrainDao;
import io.github.olgaak.dto.RouteDto;
import io.github.olgaak.dto.RoutePlanDto;
import io.github.olgaak.dto.StationDto;
import io.github.olgaak.dto.TrainDto;
import io.github.olgaak.entity.Route;
import io.github.olgaak.entity.RoutePlan;
import io.github.olgaak.entity.Ticket;
import io.github.olgaak.entity.Train;
import io.github.olgaak.exception.ActionNotAllowedException;
import io.github.olgaak.service.api.RouteService;
import io.github.olgaak.service.api.TrainService;
import io.github.olgaak.util.RouteDtoConverter;
import io.github.olgaak.util.RoutePlanDtoConverter;
import io.github.olgaak.util.TrainDtoConverter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class TrainServiceImpl implements TrainService {

    private TrainDao trainDao;

    @Autowired
    public RouteService routeService;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    public TrainServiceImpl(TrainDao trainDao) {
        this.trainDao = trainDao;
    }

    public void createNewTrain(TrainDto trainDto) {
        Train train = TrainDtoConverter.convertTrainDtoToEntity(trainDto);
        Train savedTrain = trainDao.createNewTrain(train);
        routeService.createTrainRoutes(savedTrain);
    }

    public List<TrainDto> getAllTrains() {
        List<Train> trains = trainDao.getAllTrains();
        return trains.stream().map(train -> {
            TrainDto trainDto = TrainDtoConverter.convertTrainEntityToDtoWithoutChildren(train);
            List<String> stations = new ArrayList<>();
            try {
                stations.add(train.getRoutePlan().getStartTripStation().getName());
                stations.add(train.getRoutePlan().getEndTripStation().getName());
            } catch (Exception e) {
                e.printStackTrace();
            }
            trainDto.setStations(stations);
            return trainDto;
        }).collect(Collectors.toList());
    }

    public TrainDto getTrainById(long id) {
        Train train = trainDao.getTrainById(id);
        return TrainDtoConverter.convertTrainEntityToDto(train);
    }

    @Override
    public void deleteTrain(long id) throws ActionNotAllowedException {
        Train train = trainDao.getTrainById(id);
        List<Integer> tickets = train.getRoutePlan().getRoutes().stream()
                .map(route -> route.getTickets().size())
                .filter(size-> size>0).collect(Collectors.toList());
        if(tickets.size()>0) {
            throw new ActionNotAllowedException("Train has tickets");
        } else {
            train.getRoutePlan().getRoutes().forEach(route -> {
                try {
                    routeService.deleteRoute(route.getId());
                } catch (ActionNotAllowedException e) {
                    e.printStackTrace();
                }
            });
            trainDao.deleteTrain(id);
        }


    }

    @Override
    public void editTrain(Train train) {
        trainDao.editTrain(train);
    }

}
