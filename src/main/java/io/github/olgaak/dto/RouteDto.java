package io.github.olgaak.dto;

import io.github.olgaak.util.DateTimeConverter;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class RouteDto implements Comparable<RouteDto>{

    private long id;

    private RoutePlanDto routePlan;

    private boolean isDirect = true;

    private String changeType = "direct";

    private double price;

    private int seatCount; // used to get seatcount from input form and generate seats in entity

    private List<SeatDto> seats;

    List<TicketDto> tickets;

    private Date departureDate;

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

    public RoutePlanDto getRoutePlan() {
        return routePlan;
    }

    public void setRoutePlan(RoutePlanDto routePlan) {
        this.routePlan = routePlan;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Date getDepartureDate() {
        return departureDate;
    }

    public String getFormattedDepartureDate(){
        return DateTimeConverter.parseDateToString(departureDate, "dd.MM.yyyy");
    }

    public void setDepartureDate(Date departureDate) {
        this.departureDate = departureDate;
    }

    @Override
    public int compareTo(RouteDto o) {
        return (int) (departureDate.getTime() - o.departureDate.getTime());
    }
}
