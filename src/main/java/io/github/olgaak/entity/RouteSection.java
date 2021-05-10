package io.github.olgaak.entity;

import javax.persistence.*;

@Entity
@Table(name="route_section")
public class RouteSection {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private long id;

    @ManyToOne
    @JoinColumn(name = "start_station_id")
    private Station startStation;

    @ManyToOne
    @JoinColumn(name = "end_station_id")
    private Station endStation;

    @ManyToOne
    @JoinColumn(name = "seat_id")
    private Seat seat;

    public RouteSection(Station startStation, Station endStation) {
        this.startStation = startStation;
        this.endStation = endStation;
    }

    public RouteSection() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Station getStartStation() {
        return startStation;
    }

    public void setStartStation(Station startStation) {
        this.startStation = startStation;
    }

    public Station getEndStation() {
        return endStation;
    }

    public void setEndStation(Station endStation) {
        this.endStation = endStation;
    }
}
