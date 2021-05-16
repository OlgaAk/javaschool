package io.github.olgaak.dto;

public class TicketDto {

    private long id;

    private PassengerDto passenger;

    private long routeId;

    private RouteDto route;

    private RouteSectionDto routeSection;

    private StationDto startStation;

    private StationDto endStation;

//    private long tripStartStationId;
//
//    private long tripEndStationId;

    private SeatDto seat;

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

    public long getRouteId() {
        return routeId;
    }

    public void setRouteId(long routeId) {
        this.routeId = routeId;
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
