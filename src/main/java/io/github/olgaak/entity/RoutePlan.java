package io.github.olgaak.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

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


}
