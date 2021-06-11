package io.github.olgaak.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "station")
public class Station {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private long id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @OneToMany(mappedBy = "station", fetch = FetchType.EAGER)
    private Set<TimetableItem> timetableItems;

    @ManyToMany(mappedBy = "stations", fetch = FetchType.EAGER)
    private Set<Train> trains;

    @ManyToMany(mappedBy = "stations", fetch = FetchType.EAGER)
    private Set<Route> routes;

    public Station() {}

    public Station(long id) {
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Train> getTrains() {
        return trains;
    }

    public void setTrains(Set<Train> trains) {
        this.trains = trains;
    }
}
