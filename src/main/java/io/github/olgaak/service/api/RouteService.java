package io.github.olgaak.service.api;

import io.github.olgaak.entity.Route;

import java.util.List;

public interface RouteService {

Route createNewRoute(Route timetableItem);

public List<Route> getAllRoutes();

void deleteRoute(long id);

void editRoute(Route timetableItem);
}
