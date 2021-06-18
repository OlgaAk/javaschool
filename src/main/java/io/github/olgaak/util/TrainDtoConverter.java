package io.github.olgaak.util;

import io.github.olgaak.dto.TrainDto;
import io.github.olgaak.entity.Train;

public class TrainDtoConverter {

    public static TrainDto convertTrainEntityToDto(Train train){
        TrainDto trainDto = new TrainDto();
        trainDto.setId(train.getId());
        trainDto.setNumber(train.getNumber());
        trainDto.setSeatCount(train.getSeatCount());
        trainDto.setRoutePlan(RoutePlanDtoConverter.convertRoutePlanEntityToDto(train.getRoutePlan()));
        return trainDto;
    }

    public static TrainDto convertTrainEntityToDtoWithoutChildren(Train train){
        TrainDto trainDto = new TrainDto();
        trainDto.setId(train.getId());
        trainDto.setNumber(train.getNumber());
        trainDto.setSeatCount(train.getSeatCount());
        return trainDto;
    }

    public static Train convertTrainDtoToEntity(TrainDto trainDto){
        Train train = new Train();
        train.setId(trainDto.getId());
        train.setNumber(trainDto.getNumber());
        train.setSeatCount(trainDto.getSeatCount());
        train.setRoutePlan(RoutePlanDtoConverter.convertRoutePlanDtoToEntity(trainDto.getRoutePlan()));
        train.getRoutePlan().setTrain(train);
        return train;
    }

}
