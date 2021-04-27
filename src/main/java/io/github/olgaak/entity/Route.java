package io.github.olgaak.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "route")
public class Route {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private long id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "train_id")
    private Train train;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "route_id")
    private List<TimetableItem> timetableItems;

    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "route_station",
            joinColumns = @JoinColumn(name = "route_id"),
            inverseJoinColumns = @JoinColumn(name = "station_id")
    )
    private Set<Station> stations;

    public Route() {
    }

    public Route(String id) {
        this.id = Long.parseLong(id);
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

    public List<TimetableItem> getTimetableItems() {
        return timetableItems;
    }

    public void setTimetableItems(List<TimetableItem> timetableItems) {
        this.timetableItems = timetableItems;
    }
    public Set<Station> getStations() {
        return stations;
    }

    public void setStations(Set<Station> stations) {
        this.stations = stations;
    }

}
