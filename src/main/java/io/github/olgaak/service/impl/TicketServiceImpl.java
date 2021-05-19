package io.github.olgaak.service.impl;

import io.github.olgaak.dao.api.TicketDao;
import io.github.olgaak.dto.TicketDto;
import io.github.olgaak.entity.Ticket;
import io.github.olgaak.service.api.TicketService;
import io.github.olgaak.util.TicketDtoConverter;
import io.github.olgaak.util.TicketValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TicketServiceImpl implements TicketService {

    @Autowired
    private TicketDao ticketDao;

    @Override
    public void buyTicket(TicketDto ticketDto) {
        boolean purchaseIsValid = TicketValidator.checkIfCanBuyTicket(ticketDto);
        if(purchaseIsValid) {
            Ticket ticket = TicketDtoConverter.convertTicketDtoToEntity(ticketDto);
            ticketDao.saveTicket(ticket);
        }
    }

    @Override
    public List<TicketDto> getUserTickets(long userId) {
        List<Ticket> tickets = ticketDao.getUserTickets(userId);
        return tickets.stream().map(ticket -> TicketDtoConverter.convertTicketEntityToDto(ticket)).collect(Collectors.toList());
    }

    @Override
    public void deleteTicket(long id) {
        ticketDao.deleteTicket(id);
    }
}
