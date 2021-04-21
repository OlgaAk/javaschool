package io.github.olgaak.dao;

import io.github.olgaak.entity.Route;

import java.util.List;

public interface RouteDao {

    void createNewRoute(Route route);

    List<Route> getAllRoutes();

    void deleteRoute(long id);

    void editRoute(Route route);
}
