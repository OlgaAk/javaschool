package io.github.olgaak.service.api;

import io.github.olgaak.dto.RouteDto;
import io.github.olgaak.dto.TrainQueryDto;
import io.github.olgaak.entity.Route;

import java.util.List;

public interface RouteService {

    Route createNewRoute(RouteDto timetableItem);

    List<Route> getAllRoutes();

    List<RouteDto> getTrainRoutes(Long trainId);

    RouteDto getRouteById(Long routeId);

    List<RouteDto> getTrainRoutesByQuery(TrainQueryDto trainQuery);

    void deleteRoute(long id);

    void editRoute(RouteDto route);
}
