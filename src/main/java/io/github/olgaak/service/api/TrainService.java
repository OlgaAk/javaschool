package io.github.olgaak.service.api;

import io.github.olgaak.dto.TrainDto;
import io.github.olgaak.entity.Train;
import io.github.olgaak.exception.ActionNotAllowedException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TrainService {
    void createNewTrain(TrainDto trainDto);

    List<TrainDto> getAllTrains();

    TrainDto getTrainById(long id);

    void deleteTrain(long id) throws ActionNotAllowedException;

    void editTrain(Train train);
}
