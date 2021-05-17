package io.github.olgaak.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class TicketDto {

    private long id;

    private PassengerDto passenger;

    @JsonIgnore
    private RouteDto route;

    private long routeId;

    private RouteSectionDto routeSection;

    private StationDto startStation;

    private StationDto endStation;

//    private long tripStartStationId;
//
//    private long tripEndStationId;

    @JsonIgnore
    private SeatDto seat;

    private long seatId;

    private float price;

    public TicketDto(){ }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public PassengerDto getPassenger() {
        return passenger;
    }

    public void setPassenger(PassengerDto passenger) {
        this.passenger = passenger;
    }

    public SeatDto getSeat() {
        return seat;
    }

    public void setSeat(SeatDto seat) {
        this.seat = seat;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public RouteSectionDto getRouteSection() {
        return routeSection;
    }

    public void setRouteSection(RouteSectionDto routeSection) {
        this.routeSection = routeSection;
    }

    public RouteDto getRoute() {
        return route;
    }

    public void setRoute(RouteDto route) {
        this.route = route;
    }

    public StationDto getStartStation() {
        return startStation;
    }

    public void setStartStation(StationDto startStation) {
        this.startStation = startStation;
    }

    public StationDto getEndStation() {
        return endStation;
    }

    public void setEndStation(StationDto endStation) {
        this.endStation = endStation;
    }

    public long getSeatId() {
        return seatId;
    }

    public void setSeatId(long seatId) {
        this.seatId = seatId;
    }

    public long getRouteId() {
        return routeId;
    }

    public void setRouteId(long routeId) {
        this.routeId = routeId;
    }

    //
//    public long getTripStartStationId() {
//        return tripStartStationId;
//    }
//
//    public void setTripStartStationId(long tripStartStationId) {
//        this.tripStartStationId = tripStartStationId;
//    }
//
//    public long getTripEndStationId() {
//        return tripEndStationId;
//    }
//
//    public void setTripEndStationId(long tripEndStationId) {
//        this.tripEndStationId = tripEndStationId;
//    }
}
