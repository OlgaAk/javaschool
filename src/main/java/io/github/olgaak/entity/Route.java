package io.github.olgaak.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "route")
public class Route {

    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private long id;

    @ManyToOne
    @JoinColumn(name = "train_id")
    private Train train;

    @OneToMany
    private Set<TimetableItem> timetableItems;

    @ManyToMany
    @JoinTable(name = "route_station",
            joinColumns = @JoinColumn(name = "route_id"),
            inverseJoinColumns = @JoinColumn(name = "station_id")
    )
    private Set<Station> stations;

    public Route() {
    }

    public Route(String train, String station, String departureTime) {
        System.out.println("constructer 3");
        System.out.println(train + station + departureTime);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Train getTrain() {
        return train;
    }

    public void setTrain(Train train) {
        this.train = train;
    }

    public Set<TimetableItem> getTimetableItems() {
        return timetableItems;
    }

    public void setTimetableItems(Set<TimetableItem> timetableItems) {
        this.timetableItems = timetableItems;
    }
    public Set<Station> getStations() {
        return stations;
    }

    public void setStations(Set<Station> stations) {
        this.stations = stations;
    }

}
