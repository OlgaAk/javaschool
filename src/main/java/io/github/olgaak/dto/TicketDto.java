package io.github.olgaak.dto;

public class TicketDto {

    private String id;

    private PassengerDto passengerDto;

    private long routeId;

    private int seatNumber;

    private float price;

    public TicketDto(PassengerDto passengerDto, long routeId, int seatNumber, float price) {
        this.passengerDto = passengerDto;
        this.routeId = routeId;
        this.seatNumber = seatNumber;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public PassengerDto getPassengerDto() {
        return passengerDto;
    }

    public void setPassengerDto(PassengerDto passengerDto) {
        this.passengerDto = passengerDto;
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

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
