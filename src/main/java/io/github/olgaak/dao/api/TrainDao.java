package io.github.olgaak.dao.api;

import io.github.olgaak.entity.Train;

import java.util.List;

public interface TrainDao {

    void createNewTrain(Train train);

    List<Train> getAllTrains();

    void deleteTrain(long id);

    void editTrain(Train train);
}
