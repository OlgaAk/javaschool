package io.github.olgaak.dto;


import java.util.Date;

public class TimetableItemDto implements Comparable<TimetableItemDto> {

    private long id;

    private long stationId;

    private String stationName;

    private String departureTime;

    private String arrivalTime;

    private Date departureTimeAsDate;

    private Date arrivalTimeAsDate;

    private String startTripStationName;

    private String endTripStationName;

    private int order;

    public TimetableItemDto() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    } // get data from request

    public Date getDepartureTimeAsDate() {
        return departureTimeAsDate;
    }

    public void setDepartureTimeAsDate(Date departureTimeAsDate) {
        this.departureTimeAsDate = departureTimeAsDate;
    }

    public void setArrivalTimeAsDate(Date arrivalTimeAsDate) {
        this.arrivalTimeAsDate = arrivalTimeAsDate;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(String arrivalTime) { // get data from request
        this.arrivalTime = arrivalTime;
    }

    public String getStartTripStationName() {
        return startTripStationName;
    }

    public void setStartTripStationName(String startTripStationName) {
        this.startTripStationName = startTripStationName;
    }

    public String getEndTripStationName() {
        return endTripStationName;
    }

    public void setEndTripStationName(String endTripStationName) {
        this.endTripStationName = endTripStationName;
    }

    public long getStationId() {
        return stationId;
    }

    public void setStationId(long stationId) {
        this.stationId = stationId;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public Date getArrivalTimeAsDate() {
        return arrivalTimeAsDate;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    @Override
    public int compareTo(TimetableItemDto o) {
       return o.getOrder() - order;
    }
}

