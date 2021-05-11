package io.github.olgaak.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Table(name = "timetableItem")
public class TimetableItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private long id;

    @Column(name = "departure_time", nullable = false)
    private Date departureTime;

    @Column(name = "departure_date")
    private Date departureDate;

//    @Column(name = "arrival_time")
//    private Date arrivalTime;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "station_id")
    private Station station;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "train_id")
    private Train train;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "route_id")
    private Route route;

    public TimetableItem() {
    }

    public TimetableItem(String departureTime, long stationId) {
        this.station = new Station(stationId);
    }

    public TimetableItem(String id) {
        this.id = Long.parseLong(id);
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(Date departureTime) {
        this.departureTime = departureTime;
    }

    public void setDepartureDate(Date date) {
        this.departureDate = date;
    }

    public Date getFullDepartureDate() {
        Date date = departureDate;
        int minutes = departureTime.getMinutes();
        int hours = departureTime.getHours();
        date.setMinutes(minutes);
        date.setHours(hours);
        return date;
    }

    public Date getDepartureDate() {
        return departureDate;
    }

    public Station getStation() {
        return station;
    }

    public void setStation(Station station) {
        this.station = station;
    }

    public Train getTrain() {
        return train;
    }

    public void setTrain(Train train) {
        this.train = train;
    }

}
