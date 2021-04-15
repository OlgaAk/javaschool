package io.github.olgaak.entity;

public class Station {

    private String name;
    private TimeTable[] timetable;

    public Station(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TimeTable[] getTimetable() {
        return timetable;
    }

    public void setTimetable(TimeTable[] timetable) {
        this.timetable = timetable;
    }

}
