package io.github.olgaak.entity;

import javax.persistence.*;

@Entity
@Table(name = "station")
public class Station {

    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private long id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

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
