package io.github.olgaak.service.impl;

import io.github.olgaak.dao.api.TrainDao;
import io.github.olgaak.entity.Seat;
import io.github.olgaak.entity.Train;
import io.github.olgaak.service.api.TrainService;
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
    public TrainServiceImpl(TrainDao trainDao){
        this.trainDao = trainDao;
    }

    public Train createNewTrain(Train train) {
        Set<Seat> seats = new HashSet<>();
        for(int i= 1; i<= train.getSeat_count(); i++){
            Seat seat = new Seat(i, train);
            seats.add(seat);
        }
        train.setSeats(seats);
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
