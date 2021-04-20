package io.github.olgaak.entity;

import javax.persistence.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;

@Entity
@Table(name = "timetableItem")
public class TimetableItem {
    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private long id;

    @Column(name = "departure_time", nullable = false)
    private Date departureTime;

//    @Column(name = "arrival_time")
//    private Date arrivalTime;

    @ManyToOne
    @JoinColumn(name = "station_id")
    private Station station;

    @ManyToOne
    @JoinColumn(name = "train_id")
    private Train train;

    @ManyToOne
    @JoinColumn(name = "route_id")
    private Route route;

    public TimetableItem() {
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

    public void setDepartureTime(String departureTime) {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        Date date = null;
        try {
            date = format.parse(departureTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        this.departureTime = date;
    }

//    public Date getArrivalTime() {
//        return arrivalTime;
//    }
//
//    public void setArrivalTime(Date arrivalTime) {
//        this.arrivalTime = arrivalTime;
//    }
//
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
