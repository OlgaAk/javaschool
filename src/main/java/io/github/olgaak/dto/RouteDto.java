package io.github.olgaak.dto;

import java.util.List;
import java.util.stream.Collectors;

public class RouteDto {

    private long id;

    private StationDto startTripStation;

    private StationDto endTripStation;

    private String endTripTime;

    private String startTripTime;

    private String tripDuration;

    private long tripDurationMilli;

    private boolean isDirect = true;

    private String changeType = "direct";

    private long price;

    private long trainId;

    private TrainDto train;

    private int seatCount; // used to get seatcount from input form and generate seats in entity

    private List<SeatDto> seats;

    List<TimetableItemDto> timetableItems;

    public RouteDto() {
    }

    public RouteDto(String id) {
        this.id = Long.parseLong(id);
    }

    public int getSeatCount() {
        return seatCount;
    }

    public void setSeatCount(int seatCount) {
        this.seatCount = seatCount;
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

    public StationDto getStartTripStation() {
        return startTripStation;
    }

    public void setStartTripStation(StationDto startTripStation) {
        this.startTripStation = startTripStation;
    }

    public StationDto getEndTripStation() {
        return endTripStation;
    }

    public void setEndTripStation(StationDto endTripStation) {
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

    public List<TimetableItemDto> getTimetableItems() {
        return timetableItems;
    }

    public void setTimetableItems(List<TimetableItemDto> timetableItems) {
        this.timetableItems = timetableItems;
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

    public long getTrainId() {
        return trainId;
    }

    public void setTrainId(long trainId) {
        this.trainId = trainId;
    }

    public List<SeatDto> getSeats() {
        return seats;
    }

    public void setSeats(List<SeatDto> seats) {
        this.seats = seats.stream().sorted().collect(Collectors.toList());
    }

    public TrainDto getTrain() {
        return train;
    }

    public void setTrain(TrainDto train) {
        this.train = train;
    }


}
