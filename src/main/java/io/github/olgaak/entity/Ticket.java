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

    @ManyToOne
    @JoinColumn(name = "seat_id")
    private Seat seat;

    @Column(name = "price", nullable = false)
    private float price;

    @ManyToOne
    @JoinColumn(name = "start_station_id")
    private Station startStation;

    @ManyToOne
    @JoinColumn(name = "end_station_id")
    private Station endStation;

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

    public Seat getSeat() {
        return seat;
    }

    public void setSeat(Seat seat) {
        this.seat = seat;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Station getStartStation() {
        return startStation;
    }

    public void setStartStation(Station startStation) {
        this.startStation = startStation;
    }

    public Station getEndStation() {
        return endStation;
    }

    public void setEndStation(Station endStation) {
        this.endStation = endStation;
    }
}
