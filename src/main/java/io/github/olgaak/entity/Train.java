package io.github.olgaak.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "train")
public class Train implements Serializable {

    private static final long serialVersionUID = 1l;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private long id;

    @Column(name = "number", nullable = false)
    private int number;

    @Column(name = "seat_count", nullable = false)
    private int seat_count;

    public Train(int number, int seat_count) {
        this.number = number;
        this.seat_count = seat_count;
    }

    public Train() {
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

}
