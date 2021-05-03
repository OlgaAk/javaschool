package io.github.olgaak.service.impl;

import io.github.olgaak.dao.api.TrainDao;
import io.github.olgaak.entity.Train;
import io.github.olgaak.service.api.TrainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class TrainServiceImpl implements TrainService {

    private TrainDao trainDao;

    @Autowired
    public TrainServiceImpl(TrainDao trainDao){
        this.trainDao = trainDao;
    }

    public Train createNewTrain(Train train) {
        trainDao.createNewTrain(train);
        return null;
    }

    @Transactional
    public List<Train> getAllTrains() {
        return trainDao.getAllTrains();
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
