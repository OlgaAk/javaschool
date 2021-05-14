package io.github.olgaak.dto;

import java.util.Date;

public class TimetableItemDto implements Comparable<TimetableItemDto> {

    private long id;

    private StationDto station;

    private long trainId;

    private String departureTime;

    private String departureDate;

    private Date departureTimeAsDate;

    private Date departureDateAsDate;

    private Date fullDepartureDate;

    public TimetableItemDto() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public StationDto getStation() {
        return station;
    }

    public void setStation(StationDto station) {
        this.station = station;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public String getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(String departureDate) {
        this.departureDate = departureDate;
    }

    public Date getDepartureTimeAsDate() {
        return departureTimeAsDate;
    }

    public void setDepartureTimeAsDate(Date departureTimeAsDate) {
        this.departureTimeAsDate = departureTimeAsDate;
    }

    public Date getDepartureDateAsDate() {
        return departureDateAsDate;
    }

    public void setDepartureDateAsDate(Date departureDateAsDate) {
        this.departureDateAsDate = departureDateAsDate;
    }

    public Date getFullDepartureDate() {
        return fullDepartureDate;
    }

    public void setFullDepartureDate(Date fullDepartureDate) {
        this.fullDepartureDate = fullDepartureDate;
    }

    public long getTrainId() {
        return trainId;
    }

    public void setTrainId(long trainId) {
        this.trainId = trainId;
    }

    //This method is sorting timetable by departure time
    @Override
    public int compareTo(TimetableItemDto tI) {
        return (int) (fullDepartureDate.getTime() - (tI.getFullDepartureDate().getTime()));
    }
}
