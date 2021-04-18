package io.github.olgaak.service.api;

import io.github.olgaak.entity.Train;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TrainService {
    public Train createNewTrain(Train train);

    List<Train> getAllTrains();

    void deleteTrain(long id);

    void editTrain(Train train);
}
