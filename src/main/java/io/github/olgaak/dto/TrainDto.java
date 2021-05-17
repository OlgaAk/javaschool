package io.github.olgaak.dto;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class TrainDto {

    private long id;
    private int number;
    private int seatCount;

    private List<String> stations  = new ArrayList<>();;

    private List<String> stationsUnique = new ArrayList<>();

    public TrainDto() {
    }

    public TrainDto(int number, int seat_count) {
        this.number = number;
        this.seatCount = seat_count;
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

    public int getSeatCount() {
        return seatCount;
    }

    public void setSeatCount(int seatCount) {
        this.seatCount = seatCount;
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
