package io.github.olgaak.dto;

public class RouteSectionDto {

    private long id;

    private StationDto startStation;

    private StationDto endStation;

    public RouteSectionDto() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
}
