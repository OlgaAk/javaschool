package io.github.olgaak.dto;

import java.util.List;
import java.util.stream.Collectors;

public class RouteDto {

    private long id;

    private RoutePlanDto routePlan;

    private boolean isDirect = true;

    private String changeType = "direct";

    private long price;

    private int seatCount; // used to get seatcount from input form and generate seats in entity

    private List<SeatDto> seats;

    List<TicketDto> tickets;

    public RouteDto() {
    }

    public RouteDto(String id) {
        this.id = Long.parseLong(id);
    }

    public int getSeatCount() {
        return seatCount;
    }

    public void setSeatCount(int seatCount) {
        this.seatCount = seatCount;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isDirect() {
        return isDirect;
    }

    public void setDirect(boolean direct) {
        isDirect = direct;
    }

    public String getChangeType() {
        if (isDirect) return "direct";
        return "change";
    }

    public void setChangeType(String changeType) {
        this.changeType = changeType;
    }

    public List<SeatDto> getSeats() {
        return seats;
    }

    public void setSeats(List<SeatDto> seats) {
        this.seats = seats.stream().sorted().collect(Collectors.toList());
    }

    public List<TicketDto> getTickets() {
        return tickets;
    }

    public void setTickets(List<TicketDto> tickets) {
        this.tickets = tickets;
    }
}
