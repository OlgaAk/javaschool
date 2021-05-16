package io.github.olgaak.dao.api;

import io.github.olgaak.entity.Ticket;

import java.util.List;

public interface TicketDao {

    void saveTicket(Ticket ticket);

    List<Ticket> getUserTickets(long userId);

    public void deleteTicket(long id);

}
