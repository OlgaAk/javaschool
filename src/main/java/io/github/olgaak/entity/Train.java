package io.github.olgaak.entity;

public class Train {

    private int number;
    private int seat_count;
    private Station[] stations;

    public Train(int number, int seat_count, Station[] stations) {
        this.number = number;
        this.seat_count = seat_count;
        this.stations = stations;
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

    public Station[] getStations() {
        return stations;
    }

    public void setStations(Station[] stations) {
        this.stations = stations;
    }
}
