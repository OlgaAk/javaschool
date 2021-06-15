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

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "routeplan_id")
    private Set<TimetableItem> timetableItems = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
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

}
