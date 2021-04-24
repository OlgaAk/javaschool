package io.github.olgaak.service.impl;

import io.github.olgaak.dao.api.RouteDao;
import io.github.olgaak.entity.Route;
import io.github.olgaak.service.api.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RouteServiceImpl implements RouteService {

    private RouteDao routeDao;

    @Autowired
    public RouteServiceImpl(RouteDao routeDao){
        this.routeDao = routeDao;
    }

    public Route createNewRoute(Route route) {
        routeDao.createNewRoute(route);
        return null;
    }

    public List<Route> getAllRoutes() {
        return routeDao.getAllRoutes();
    }

    @Override
    public void deleteRoute(long id) {
        routeDao.deleteRoute(id);
    }

    @Override
    public void editRoute(Route timetableItem) {
        routeDao.editRoute(timetableItem);
    }

}
