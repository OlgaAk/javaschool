package io.github.olgaak.dto;

public class SeatDto implements Comparable <SeatDto>{

    private int id;
    private int number;
    private boolean isVacant;

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
        return isVacant;
    }

    public void setVacant(boolean vacant) {
        isVacant = vacant;
    }

    // Method used to sort seats set by number into list
    @Override
    public int compareTo(SeatDto s) {
        return number - s.number;
    }
}
