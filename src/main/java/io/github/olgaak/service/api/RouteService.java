package io.github.olgaak.service.api;

import io.github.olgaak.dto.RouteDto;
import io.github.olgaak.dto.TrainQueryDto;
import io.github.olgaak.entity.Route;

import java.util.List;

public interface RouteService {

Route createNewRoute(Route timetableItem);

public List<Route> getAllRoutes();

public List<Route> getTrainRoutes(Long trainId);

public List<RouteDto> getTrainRoutesByQuery(TrainQueryDto trainQuery);

void deleteRoute(long id);

void editRoute(Route timetableItem);
}
