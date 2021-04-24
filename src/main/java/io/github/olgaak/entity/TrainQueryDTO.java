package io.github.olgaak.entity;

public class TrainQueryDTO {
    private String departureStation;
    private String arrivalStation;
    private String departureDate;

    public TrainQueryDTO() {
    }

    public TrainQueryDTO(String departureStation, String arrivalStation, String departureDate) {
        this.departureStation = departureStation;
        this.arrivalStation = arrivalStation;
        this.departureDate = departureDate;
    }

    public String getDepartureStation() {
        return departureStation;
    }

    public void setDepartureStation(String departureStation) {
        this.departureStation = departureStation;
    }

    public String getArrivalStation() {
        return arrivalStation;
    }

    public void setArrivalStation(String arrivalStation) {
        this.arrivalStation = arrivalStation;
    }

    public String getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(String departureDate) {
        this.departureDate = departureDate;
    }

}
