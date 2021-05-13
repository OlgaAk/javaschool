package io.github.olgaak.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "seat")
public class Seat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private long id;

    @Column(name = "number", nullable = false)
    private int number;
//
//    @Column(name = "is_vacant", nullable = false)
//    private boolean isVacant = true;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "seat_id")
    private Set<RouteSection> takenRouteSections = new HashSet<>();

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "route_id")
    private Route route;

    public Seat(long id) {
        this.id = id;
    }

    public Seat() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Set<RouteSection> getTakenRouteSections() {
        return takenRouteSections;
    }

    public void setTakenRouteSections(Set<RouteSection> takenRouteSections) {
        this.takenRouteSections = takenRouteSections;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }
}