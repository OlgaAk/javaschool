package io.github.olgaak.entity;

import javax.persistence.*;

@Entity
@Table(name = "ticket")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private long id;

    @ManyToOne
    @JoinColumn(name = "passenger_id")
    private Passenger passenger;

//    @Column(name = "train_id", nullable = false)
//    private long trainId;

    @ManyToOne
    @JoinColumn(name = "route_id")
    private Route route;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "seat_id", referencedColumnName = "id")
    private Seat seat;

    @Column(name = "price", nullable = false)
    private float price;

    public Ticket() { }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
//
//    public long getTrainId() {
//        return trainId;
//    }
//
//    public void setTrainId(long trainId) {
//        this.trainId = trainId;
//    }


    public Passenger getPassenger() {
        return passenger;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }
}
