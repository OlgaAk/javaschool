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

    @Column(name = "departure_time")
    private Date departureTime;

    @Column(name = "arrival_time")
    private Date arrivalTime;

    @Column(name = "order_num") // order items (order by departure time
    // is complicated because if several days - date is needed, but date belongs to route entity )
    private int order;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "station_id")
    private Station station;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "train_id")
    private Train train;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "routeplan_id")
    private RoutePlan routePlan;

    public TimetableItem() {
    }

    public TimetableItem(String departureTime, long stationId) {
        this.station = new Station(stationId);
    }

    public TimetableItem(String id) {
        this.id = Long.parseLong(id);
    }

    public RoutePlan getRoutePlan() {
        return routePlan;
    }

    public void setRoutePlan(RoutePlan routePlan) {
        this.routePlan = routePlan;
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

    public Date getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(Date arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public Date getFullDepartureDate(Date date) {
        int minutes = departureTime.getMinutes();
        int hours = departureTime.getHours();
        date.setMinutes(minutes);
        date.setHours(hours);
        return date;
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

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }
}
