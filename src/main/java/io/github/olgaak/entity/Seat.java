package io.github.olgaak.entity;

import javax.persistence.*;

@Entity
@Table(name = "seat")
public class Seat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private long id;

    @Column(name = "number", nullable = false)
    private int number;

    @Column(name = "is_vacant", nullable = false)
    private boolean isVacant;

    @ManyToOne
    @JoinColumn(name = "route_id")
    private Route route;


}