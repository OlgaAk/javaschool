package io.github.olgaak.service.impl;

import io.github.olgaak.dao.api.RouteDao;
import io.github.olgaak.dto.RouteDto;
import io.github.olgaak.dto.TrainQueryDto;
import io.github.olgaak.entity.Route;
import io.github.olgaak.entity.Seat;
import io.github.olgaak.entity.Station;
import io.github.olgaak.entity.TimetableItem;
import io.github.olgaak.exception.ActionNotAllowedException;
import io.github.olgaak.service.MessageSender;
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

    public Route createNewRoute(RouteDto routeDto) {
        Route route = RouteDtoConverter.convertRouteDtoToEntity(routeDto);
        Set<Station> stations = new HashSet<>();
        for (TimetableItem timetable : route.getTimetableItems()) {
            timetable.setTrain(route.getTrain());
            stations.add(timetable.getStation());
        }
        route.setStations(stations);
        Set<Seat> seats = new HashSet<>();
        for(int i = 1; i<= routeDto.getSeatCount(); i++){
            Seat seat = new Seat(i);
            seat.setRoute(route);
            seats.add(seat);
        }
        route.setSeats(seats);
        routeDao.createNewRoute(route);
        return null;
    }

    public List<Route> getAllRoutes() {
        return routeDao.getAllRoutes();
    }

    public List<RouteDto> getTrainRoutes(Long trainId) {
        List<RouteDto> routeDtoList = new ArrayList<>();
        List<Route> routes = routeDao.getTrainRoutes(trainId);
        routeDtoList = routes.stream().map(route -> RouteDtoConverter.convertRouteEntityToDto(route)).collect(Collectors.toList());
        return routeDtoList;
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
    public void deleteRoute(long id) throws ActionNotAllowedException {
        Route route = routeDao.getRouteById(id);
        if(route.getTickets().size()>0){
            throw new ActionNotAllowedException("Route has tickets");
        } else {
            routeDao.deleteRoute(id);
        }
    }

    @Override
    public void editRoute(RouteDto routeDto) {
        Route route = RouteDtoConverter.convertRouteDtoToEntity(routeDto);
        routeDao.editRoute(route);
    }

}
