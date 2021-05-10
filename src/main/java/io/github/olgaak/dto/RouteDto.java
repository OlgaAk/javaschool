package io.github.olgaak.dto;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class RouteDto {

    private long id;

    private String startTripStation;

    private String endTripStation;

    private String endTripTime;

    private String startTripTime;

    private String tripDuration;

    private long tripDurationMilli;

    private boolean isDirect = true;

    private String changeType;

    private long price;

    private long train_id;

    private List<SeatDto> seats;

    List<TimetableItemDto> timetableItemDtoList;

    public RouteDto() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTripDuration() {
        return tripDuration;
    }

    public void setTripDuration(String tripDuration) {
        this.tripDuration = tripDuration;
    }

    public boolean isDirect() {
        return isDirect;
    }

    public void setDirect(boolean direct) {
        isDirect = direct;
    }

    public String getChangeType() {
        if (isDirect) return "direct";
        return "change";
    }

    public long getPrice() {
        return tripDurationMilli / 60000 / 15;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public String getStartTripStation() {
        return startTripStation.toUpperCase();
    }

    public void setStartTripStation(String startTripStation) {
        this.startTripStation = startTripStation;
    }

    public String getEndTripStation() {
        return endTripStation.toUpperCase();
    }

    public void setEndTripStation(String endTripStation) {
        this.endTripStation = endTripStation;
    }

    public String getEndTripTime() {

        return endTripTime.split(" ")[1].substring(0, 5);
    }

    public void setEndTripTime(String endTripTime) {
        this.endTripTime = endTripTime;
    }

    public String getStartTripTime() {

        return startTripTime.split(" ")[1].substring(0, 5);
    }

    public void setStartTripTime(String startTripTime) {
        this.startTripTime = startTripTime;
    }

    public List<TimetableItemDto> getTimetableItemDtoList() {
        return timetableItemDtoList;
    }

    public void setTimetableItemDtoList(List<TimetableItemDto> timetableItemDtoList) {
        this.timetableItemDtoList = timetableItemDtoList;
    }

    public long getTripDurationMilli() {
        return tripDurationMilli;
    }

    public void setTripDurationMilli(long tripDurationMilli) {
        this.tripDurationMilli = tripDurationMilli;
    }

    public void setChangeType(String changeType) {
        this.changeType = changeType;
    }

    public long getTrain_id() {
        return train_id;
    }

    public void setTrain_id(long train_id) {
        this.train_id = train_id;
    }

    public List<SeatDto> getSeats() {
        return seats;
    }

    public void setSeats(List<SeatDto> seats) {
        this.seats = seats.stream().sorted().collect(Collectors.toList());
    }
}
