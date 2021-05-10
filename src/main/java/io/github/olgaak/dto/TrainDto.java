package io.github.olgaak.dto;


import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class TrainDto {

    private int number;
    private int seat_count;

    private List<RouteDto> routes;

    public TrainDto() {
    }

    public TrainDto(int number, int seat_count) {
        this.number = number;
        this.seat_count = seat_count;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getSeat_count() {
        return seat_count;
    }

    public void setSeat_count(int seat_count) {
        this.seat_count = seat_count;
    }

    public List<RouteDto> getRoutes() {
        return routes;
    }

    public void setRoutes(List<RouteDto> routes) {
        this.routes = routes;
    }
}
