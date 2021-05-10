package io.github.olgaak.service.api;

import io.github.olgaak.dto.TrainDto;
import io.github.olgaak.entity.Train;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TrainService {
    Train createNewTrain(Train train);

    List<TrainDto> getAllTrains();

    TrainDto getTrainById(long id);

    void deleteTrain(long id);

    void editTrain(Train train);
}
