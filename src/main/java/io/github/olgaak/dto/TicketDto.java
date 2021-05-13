package io.github.olgaak.dto;

public class TicketDto {

    private String id;

    private PassengerDto passenger;

    private int routeId;

    private int seatNumber;

    private float price;

    public TicketDto(){ System.out.println("ticketdto constructer 1");}

    public TicketDto(int routeId, int seatNumber, float price, PassengerDto passenger) {
        System.out.println("ticketdto constructer 2");
        this.passenger = passenger;
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

    public PassengerDto getPassenger() {
        return passenger;
    }

    public void setPassengerDto(PassengerDto passenger) {
        this.passenger = passenger;
    }

    public int getRouteId() {
        return routeId;
    }

    public void setRouteId(int routeId) {
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
