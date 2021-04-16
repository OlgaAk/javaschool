package io.github.olgaak.service.impl;

import io.github.olgaak.dao.TrainDao;
import io.github.olgaak.entity.Train;
import io.github.olgaak.service.api.TrainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}
