package io.github.olgaak.service.impl;

import io.github.olgaak.dao.api.TrainDao;
import io.github.olgaak.dto.TrainDto;
import io.github.olgaak.entity.Seat;
import io.github.olgaak.entity.Train;
import io.github.olgaak.service.api.TrainService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    public List<Train> getAllTrains() {
        return trainDao.getAllTrains();
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
