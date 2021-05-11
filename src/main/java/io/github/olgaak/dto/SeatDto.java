package io.github.olgaak.dto;

import io.github.olgaak.entity.RouteSection;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SeatDto implements Comparable <SeatDto>{

    private int id;
    private int number;
    private boolean isVacant = true;

    private List<RouteSectionDto> takenRouteSections = new ArrayList<>();

    public SeatDto() {
    }

    public SeatDto(int id, int number, boolean isVacant) {
        this.id = id;
        this.number = number;
        this.isVacant = isVacant;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public boolean getVacant() {
        return takenRouteSections.size() == 0;
    }

    public void setVacant(boolean vacant) {
        isVacant = vacant;
    }

    // Method used to sort seats set by number into list
    @Override
    public int compareTo(SeatDto s) {
        return number - s.number;
    }

    public List<RouteSectionDto> getTakenRouteSections() {
        return takenRouteSections;
    }

    public void setTakenRouteSections(List<RouteSectionDto> takenRouteSections) {
        this.takenRouteSections = takenRouteSections.stream().sorted().collect(Collectors.toList());
    }

}
