package io.github.olgaak.dao.api;

import io.github.olgaak.dto.TrainQueryDto;
import io.github.olgaak.entity.Route;

import java.util.List;

public interface RouteDao {

    void createNewRoute(Route route);

    List<Route> getAllRoutes();

    List<Route> getTrainRoutes(Long trainId);

    public List<Route> getTrainRoutesByQuery(TrainQueryDto trainQuery);

    void deleteRoute(long id);

    void editRoute(Route route);
}
