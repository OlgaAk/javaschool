package io.github.olgaak.entity;

public class Ticket {
    private int passengerId;
    private int trainId;

    public Ticket(int passengerId, int trainId) {
        this.passengerId = passengerId;
        this.trainId = trainId;
    }

    public int getPassengerId() {
        return passengerId;
    }

    public void setPassengerId(int passengerId) {
        this.passengerId = passengerId;
    }

    public int getTrainId() {
        return trainId;
    }

    public void setTrainId(int trainId) {
        this.trainId = trainId;
    }
}
