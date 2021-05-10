package io.github.olgaak.dto;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class TrainDto {

    private long id;
    private int number;
    private int seat_count;

    private List<String> stations;

    private List<String> stationsUnique;

    public TrainDto() {
    }

    public TrainDto(int number, int seat_count) {
        this.number = number;
        this.seat_count = seat_count;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public List<String> getStations() {
        return stations;
    }

    public void setStations(List<String> stations) {
        this.stations = stations;
    }

    public List<String> getStationsUnique() {
        return new ArrayList<String>(new HashSet<>(stations));
    }


}
