package io.github.olgaak.dto;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TrainQueryDto {
    private long departureStationId;
    private long arrivalStationId;
    private Date departureDate;

    public TrainQueryDto() {
    }

    public TrainQueryDto(int departureStation, int arrivalStation, String departureDate) {
    }

    public long getDepartureStationId() {
        return departureStationId;
    }

    public void setDepartureStationId(int departureStationId) {
        this.departureStationId = departureStationId;
    }

    public long getArrivalStationId() {
        return arrivalStationId;
    }

    public void setArrivalStationId(int arrivalStationId) {
        this.arrivalStationId = arrivalStationId;
    }

    public Date getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(String departureDate) {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = format.parse(departureDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        this.departureDate = date;
    }

}
