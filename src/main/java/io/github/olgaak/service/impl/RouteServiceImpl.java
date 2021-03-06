package io.github.olgaak.service.impl;

import io.github.olgaak.dao.api.RouteDao;
import io.github.olgaak.dao.api.TimetableDao;
import io.github.olgaak.dto.RouteDto;
import io.github.olgaak.dto.TrainQueryDto;
import io.github.olgaak.entity.*;
import io.github.olgaak.exception.ActionNotAllowedException;
import io.github.olgaak.service.MessageSender;
import io.github.olgaak.service.api.RouteService;
import io.github.olgaak.util.DateTimeConverter;
import io.github.olgaak.util.RouteDtoConverter;
import org.apache.commons.lang3.time.DateUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
@Transactional
public class RouteServiceImpl implements RouteService {

    private RouteDao routeDao;

    private TimetableDao timetableItemDao;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    public MessageSender messageSender;

    @Autowired
    public RouteServiceImpl(RouteDao routeDao, TimetableDao timetableItemDao){
        this.routeDao = routeDao;
        this.timetableItemDao = timetableItemDao;
    }

//    public Route createNewRoute(RouteDto routeDto) {
//        Route route = RouteDtoConverter.convertRouteDtoToEntity(routeDto);
//        Set<Station> stations = new HashSet<>();
//        for (TimetableItem timetable : route.getRoutePlan().getTimetableItems()) {
//            stations.add(timetable.getStation());
//        }
//        route.setStations(stations);
//        Set<Seat> seats = new HashSet<>();
//        for(int i = 1; i<= routeDto.getSeatCount(); i++){
//            Seat seat = new Seat(i);
//            seat.setRoute(route);
//            seats.add(seat);
//        }
//        route.setSeats(seats);
//        routeDao.createNewRoute(route);
//        return null;
//    }

    @Async
    public void createTrainRoutes(Train train) {
        List<Integer> weekdays = train.getRoutePlan().getWeekdays().stream().map(d -> d.ordinal()).collect(Collectors.toList());
        int periodLimitDays = 14;
        Calendar calendar = DateTimeConverter.createCalender();
        Set<Station> stations = new HashSet<>();
        for (TimetableItem timetable : train.getRoutePlan().getTimetableItems()) {
            stations.add(timetable.getStation());
        }
        for (int i = 0; i < periodLimitDays; i++) {
            if (weekdays.contains(calendar.get(Calendar.DAY_OF_WEEK)-1)) {
                createRoute(calendar, train, stations);
                if(i==0) messageSender.sendMessage(); //if train is today, update second app`s timetable
            }
            calendar.add(Calendar.DATE, 1);
        }
    }

    public void createRoute(Calendar calendar, Train train,  Set<Station>stations) {
        Route route = new Route();
        route.setRoutePlan(train.getRoutePlan());
        route.setTrain(train);
        Date date = calendar.getTime();
        route.setDepartureDate(date);
        route.setStations(stations);
        Set<Seat> seats = new HashSet<>();
        for (int j = 1; j <= train.getSeatCount(); j++) {
            Seat seat = new Seat(j);
            seat.setRoute(route);
            seats.add(seat);
        }
        route.setSeats(seats);
        routeDao.createNewRoute(route);
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
        List<TimetableItem> timetableItems = timetableItemDao.getRoutePlanTimetableItems(route.getRoutePlan().getId());
        route.getRoutePlan().setTimetableItems((new HashSet<>(timetableItems)));
        if(route == null) return null;
        routeDto = RouteDtoConverter.convertRouteEntityToDto(route);
        return routeDto;
    }

    public List<RouteDto> getTrainRoutesByQuery(TrainQueryDto trainQuery) {
        List<Route> routesByQuery = routeDao.getTrainRoutesByQuery(trainQuery);
        List<RouteDto> routeDtoList = new ArrayList<>();
        routesByQuery.stream().forEach(route->{
            List<TimetableItem> timetableItems = timetableItemDao.getRoutePlanTimetableItems(route.getRoutePlan().getId());
            route.getRoutePlan().setTimetableItems((new HashSet<>(timetableItems)));
            routeDtoList.add(RouteDtoConverter.convertRouteEntityToDto(route));
        });
        return routeDtoList;
    }

    @Override
    public void deleteRoute(long id) throws ActionNotAllowedException {
        Route route = routeDao.getRouteById(id);
        if(route.getTickets().size()>0){
            throw new ActionNotAllowedException("Route has tickets. You should cancel tickets first.");
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
