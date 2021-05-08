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
    private boolean isVacant = true;

//    @ManyToOne
//    @JoinColumn(name = "route_id")
//    private Route route;

    @ManyToOne
    @JoinColumn(name = "train_id")
    private Train train;

    public Seat(int number, Train train) {
        this.number = number;
        this.train = train;
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

    public boolean isVacant() {
        return isVacant;
    }

    public void setVacant(boolean vacant) {
        isVacant = vacant;
    }

    public Train getTrain() {
        return train;
    }

    public void setTrain(Train train) {
        this.train = train;
    }
}