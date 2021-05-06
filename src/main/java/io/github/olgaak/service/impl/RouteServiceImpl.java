package io.github.olgaak.service.impl;

import io.github.olgaak.dao.api.RouteDao;
import io.github.olgaak.dto.RouteDto;
import io.github.olgaak.dto.TrainQueryDto;
import io.github.olgaak.entity.Route;
import io.github.olgaak.service.api.RouteService;
import io.github.olgaak.util.RouteDtoConverter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RouteServiceImpl implements RouteService {

    private RouteDao routeDao;

    @Autowired
    ModelMapper modelMapper;

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

    public List<Route> getTrainRoutes(Long trainId) {
        return routeDao.getTrainRoutes(trainId);
    }

    public List<RouteDto> getTrainRoutesByQuery(TrainQueryDto trainQuery) {
        List<Route> routesByQuery = routeDao.getTrainRoutesByQuery(trainQuery);
        List<RouteDto> routeDtoList = new ArrayList<>();
        routesByQuery.stream().forEach(route->{
            routeDtoList.add(RouteDtoConverter.convertRouteEntityToDto(route));
        });
        return routeDtoList;
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
