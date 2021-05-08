package io.github.olgaak.dto;


import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class TrainDto {

    private int number;
    private int seat_count;
    private List<SeatDto> seats;

    public TrainDto() {
    }

    public TrainDto(int number, int seat_count, Set<SeatDto> seats) {
        this.number = number;
        this.seat_count = seat_count;
        this.seats = seats.stream().sorted().collect(Collectors.toList());
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

    public List<SeatDto> getSeats() {
        return seats;
    }

    public void setSeats(Set<SeatDto> seats) {
        this.seats = seats.stream().sorted().collect(Collectors.toList());
    }
}
