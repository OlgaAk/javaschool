package io.github.olgaak.service.api;

import io.github.olgaak.dto.TicketDto;
import io.github.olgaak.exception.ActionNotAllowedException;

import java.util.List;

public interface TicketService {

    void buyTicket(TicketDto ticketDto) throws ActionNotAllowedException;

    List<TicketDto> getUserTickets(long userId);

    public void deleteTicket(long id);
}
