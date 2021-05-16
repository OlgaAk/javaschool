package io.github.olgaak.service.api;

import io.github.olgaak.dto.TicketDto;

import java.util.List;

public interface TicketService {

    TicketDto buyTicket(TicketDto ticketDto);

    List<TicketDto> getUserTickets(long userId);

    public void deleteTicket(long id);
}
