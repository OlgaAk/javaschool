package io.github.olgaak.dto;

import java.util.List;

public class StationDto {

    private long id;

    private String name;

    private List<TimetableItemDto> timetableItems;

    private List<TrainDto> trains;

    private List<RouteDto> routes;

    public StationDto() {
    }

    public StationDto(String id){
        this.id = Long.parseLong(id);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<TimetableItemDto> getTimetableItems() {
        return timetableItems;
    }

    public void setTimetableItems(List<TimetableItemDto> timetableItems) {
        this.timetableItems = timetableItems;
    }

    public List<TrainDto> getTrains() {
        return trains;
    }

    public void setTrains(List<TrainDto> trains) {
        this.trains = trains;
    }

    public List<RouteDto> getRoutes() {
        return routes;
    }

    public void setRoutes(List<RouteDto> routes) {
        this.routes = routes;
    }

    @Override
    public String toString() {
        return "{" +
                "id:" + id +
                ", name:\"" + name + '"' +
                '}';
    }
}
