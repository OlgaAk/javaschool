package io.github.olgaak.util;

import io.github.olgaak.dto.TrainDto;
import io.github.olgaak.entity.Train;

public class TrainDtoConverter {

    public static TrainDto convertTrainEntityToDto(Train train){
        TrainDto trainDto = new TrainDto();
        trainDto.setId(train.getId());
        trainDto.setNumber(train.getNumber());
        return trainDto;
    }

    public static Train convertTrainDtoToEntity(TrainDto trainDto){
        Train train = new Train();
        train.setId(trainDto.getId());
        train.setNumber(trainDto.getNumber());
        return train;
    }

}
