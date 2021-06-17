package io.github.olgaak.service.api;

import io.github.olgaak.dto.RouteDto;
import io.github.olgaak.dto.TrainQueryDto;
import io.github.olgaak.entity.Route;
import io.github.olgaak.entity.Station;
import io.github.olgaak.entity.Train;
import io.github.olgaak.exception.ActionNotAllowedException;

import java.util.Calendar;
import java.util.List;
import java.util.Set;

public interface RouteService {

//    Route createNewRoute(RouteDto timetableItem);

    void createRoute(Calendar calendar, Train train, Set<Station> stations);

    void createTrainRoutes(Train train);

    List<Route> getAllRoutes();

    List<RouteDto> getTrainRoutes(Long trainId);

    RouteDto getRouteById(Long routeId);

    List<RouteDto> getTrainRoutesByQuery(TrainQueryDto trainQuery);

    void deleteRoute(long id) throws ActionNotAllowedException;

    void editRoute(RouteDto route);
}
