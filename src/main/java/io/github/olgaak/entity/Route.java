package io.github.olgaak.entity;

import javax.persistence.*;
import java.util.HashSet;
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
    private Set<TimetableItem> timetableItemSet;

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

    public Set<TimetableItem> getTimetableItemSet() {
        return timetableItemSet;
    }

}
