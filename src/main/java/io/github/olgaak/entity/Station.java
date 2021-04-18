package io.github.olgaak.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "station")
public class Station {

    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private long id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @OneToMany
    @JoinColumn(name = "timetableItem_id")
    private Set<TimetableItem> timetableItems;

    public Station() {}

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
}
