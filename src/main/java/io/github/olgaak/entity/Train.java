package io.github.olgaak.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "train")
public class Train implements Serializable {

    private static final long serialVersionUID = 1l;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private long id;

    @Column(name = "number", nullable = false, unique = true)
    private int number;

    @Column(name = "seat_count", nullable = false)
    private int seat_count;

    @ManyToMany(fetch = FetchType.EAGER)
        @JoinTable(name = "train_station",
            joinColumns = @JoinColumn(name = "train_id"),
            inverseJoinColumns = @JoinColumn(name = "station_id")
    )
    private Set<Station> stations;

    @OneToMany(mappedBy = "train", fetch = FetchType.EAGER)
    private Set<Route> routes;

    public Train(int number, int seat_count) {
        this.number = number;
        this.seat_count = seat_count;
    }

    public Train() {
    }

    public Train(String id) {
        this.id = Long.parseLong(id);
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

    public int getSeat_count() {
        return seat_count;
    }

    public void setSeat_count(int seat_count) {
        this.seat_count = seat_count;
    }

    public Set<Station> getStations() {
        return stations;
    }

    public void setStations(Set<Station> stations) {
        this.stations = stations;
    }
}
