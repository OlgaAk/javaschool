package io.github.olgaak.service.impl;

import io.github.olgaak.dao.api.RouteDao;
import io.github.olgaak.dto.RouteDto;
import io.github.olgaak.dto.TrainQueryDto;
import io.github.olgaak.entity.Route;
import io.github.olgaak.entity.Seat;
import io.github.olgaak.service.api.RouteService;
import io.github.olgaak.util.RouteDtoConverter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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
        Set<Seat> seats = new HashSet<>();
        for(int i= 1; i<= route.getTrain().getSeat_count(); i++){
            Seat seat = new Seat(i);
            seats.add(seat);
        }
        route.setSeats(seats);
        routeDao.createNewRoute(route);
        return null;
    }

    public List<Route> getAllRoutes() {
        return routeDao.getAllRoutes();
    }

    public List<Route> getTrainRoutes(Long trainId) {
        return routeDao.getTrainRoutes(trainId);
    }

    public RouteDto getRouteById(Long routeId) {
        RouteDto routeDto = new RouteDto();
        Route route = routeDao.getRouteById(routeId);
        if(route == null) return null;
        routeDto = RouteDtoConverter.convertRouteEntityToDto(route);
        return routeDto;
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
    public void editRoute(Route route) {
        routeDao.editRoute(route);
    }

}
