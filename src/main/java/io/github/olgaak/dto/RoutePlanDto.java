package io.github.olgaak.dto;

import java.util.Arrays;
import java.util.List;

public class RoutePlanDto {

    private long id;

    private StationDto startTripStation;

    private StationDto endTripStation;

    private String endTripTime;

    private String endTripTimeHours;

    private String startTripTime;

    private String startTripTimeHours;

    private String tripDuration;

    private long tripDurationMilli;

    private long trainId;

    private List<Integer> weekdays;

    private TrainDto train;

    List<TimetableItemDto> timetableItems;

    public List<String> getDaysOfWeekNames() {
        return Arrays.asList("Monday", "Tuesday","Wednesday", "Thursday", "Friday", "Saturday", "Sunday");
    }

    public RoutePlanDto() {
    }

    public RoutePlanDto(String id) {
        this.id = Long.parseLong(id);
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

        return endTripTime;
    }

    public void setEndTripTime(String endTripTime) {
        this.endTripTime = endTripTime;
    }

    public String getStartTripTime() {

        return startTripTime;
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

    public long getTrainId() {
        return trainId;
    }

    public void setTrainId(long trainId) {
        this.trainId = trainId;
    }

    public TrainDto getTrain() {
        return train;
    }

    public void setTrain(TrainDto train) {
        this.train = train;
    }

    public String getEndTripTimeHours() {
        return endTripTimeHours;
    }

    public void setEndTripTimeHours(String endTripTimeHours) {
        this.endTripTimeHours = endTripTimeHours;
    }

    public String getStartTripTimeHours() {
        return startTripTimeHours;
    }

    public void setStartTripTimeHours(String startTripTimeHours) {
        this.startTripTimeHours = startTripTimeHours;
    }

    public List<Integer> getWeekdays() {
        return weekdays;
    }

    public void setWeekdays(List<Integer> weekdays) {
        this.weekdays = weekdays;
    }
}
