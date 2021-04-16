package io.github.olgaak.service.api;

import io.github.olgaak.entity.Train;
import org.springframework.stereotype.Service;

@Service
public interface TrainService {
    public Train createNewTrain(Train train);
}
