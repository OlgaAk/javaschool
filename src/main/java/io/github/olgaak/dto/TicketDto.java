package io.github.olgaak.dto;

public class TicketDto {

    private String id;

    private PassengerDto passenger;

    private long routeId;

    private long tripStartStationId;

    private long tripEndStationId;

    private SeatDto seat;

    private float price;

    public TicketDto(){ }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public long getTripStartStationId() {
        return tripStartStationId;
    }

    public void setTripStartStationId(long tripStartStationId) {
        this.tripStartStationId = tripStartStationId;
    }

    public long getTripEndStationId() {
        return tripEndStationId;
    }

    public void setTripEndStationId(long tripEndStationId) {
        this.tripEndStationId = tripEndStationId;
    }
}
