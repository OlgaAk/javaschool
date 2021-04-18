package io.github.olgaak.dao;

import io.github.olgaak.entity.Train;

import java.util.List;

public interface TrainDao {
   public void createNewTrain(Train train);

    List<Train> getAllTrains();
}
