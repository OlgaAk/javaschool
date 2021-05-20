package io.github.olgaak.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;

public class TicketDto implements Comparable<TicketDto> {

    private long id;

    private PassengerDto passenger;

    @JsonIgnore
    private RouteDto route;

    private long routeId;

    private RouteSectionDto routeSection;

    private StationDto startStation;

    private String departureTime;

    private Date departureTimeAsDAte;

    private Date arrivalTimeAsDate;

    private String arrivalTime;

    private StationDto endStation;

    @JsonIgnore
    private SeatDto seat;

    private long seatId;

    private int seatNumber;

    private float price;

    private boolean isArchived = false;

    public TicketDto() {
    }

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

    public int getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public Date getArrivalTimeAsDate() {
        return arrivalTimeAsDate;
    }

    public void setArrivalTimeAsDate(Date arrivalTimeAsDate) {
        this.arrivalTimeAsDate = arrivalTimeAsDate;
    }

    public Date getDepartureTimeAsDAte() {
        return departureTimeAsDAte;
    }

    public void setDepartureTimeAsDAte(Date departureTimeAsDAte) {
        this.departureTimeAsDAte = departureTimeAsDAte;
    }

    public boolean getIsArchived() {
        return isArchived;
    }

    public void setIsArchived(boolean archived) {
        isArchived = archived;
    }

    @Override
    public int compareTo(TicketDto o) {
        return (int) (o.departureTimeAsDAte.getTime() - departureTimeAsDAte.getTime());
    }
}
