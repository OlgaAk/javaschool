package io.github.olgaak.entity;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "routeplan")
public class RoutePlan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private long id;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "routeplan_id")
    private Set<TimetableItem> timetableItems = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "routeplan_id")
    private Set<Route> routes = new HashSet<>();

    @JoinTable(name = "routeplan_weekday", joinColumns = @JoinColumn(name = "routeplan_id"))
    @Column(name = "weekday_id")
    @Enumerated(EnumType.ORDINAL)
    @ElementCollection(targetClass =Weekday.class, fetch = FetchType.EAGER)
    private Collection<Weekday> weekdays;

    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "train_id")
    private Train train;

    @ManyToOne
    @JoinColumn(name = "start_station_id")
    private Station startTripStation;

    @ManyToOne
    @JoinColumn(name = "end_station_id")
    private Station endTripStation;

    public RoutePlan() {
    }

    public RoutePlan(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Set<TimetableItem> getTimetableItems() {
        return timetableItems;
    }

    public void setTimetableItems(Set<TimetableItem> timetableItems) {
        this.timetableItems = timetableItems;
    }

    public Set<Route> getRoutes() {
        return routes;
    }

    public void setRoutes(Set<Route> routes) {
        this.routes = routes;
    }

    public Collection<Weekday> getWeekdays() {
        return weekdays;
    }

    public void setWeekdays(Collection<Weekday> weekdays) {
        this.weekdays = weekdays;
    }

    public Train getTrain() {
        return train;
    }

    public void setTrain(Train train) {
        this.train = train;
    }

    public Station getStartTripStation() {
        return startTripStation;
    }

    public void setStartTripStation(Station startTripStation) {
        this.startTripStation = startTripStation;
    }

    public Station getEndTripStation() {
        return endTripStation;
    }

    public void setEndTripStation(Station endTripStation) {
        this.endTripStation = endTripStation;
    }
}
