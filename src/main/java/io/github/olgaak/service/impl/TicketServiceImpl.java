package io.github.olgaak.service.impl;

import io.github.olgaak.dao.api.RouteDao;
import io.github.olgaak.dao.api.TicketDao;
import io.github.olgaak.dao.api.TimetableDao;
import io.github.olgaak.dto.TicketDto;
import io.github.olgaak.entity.Passenger;
import io.github.olgaak.entity.Route;
import io.github.olgaak.entity.Ticket;
import io.github.olgaak.entity.TimetableItem;
import io.github.olgaak.exception.ActionNotAllowedException;
import io.github.olgaak.service.api.TicketService;
import io.github.olgaak.util.PassengerDtoConverter;
import io.github.olgaak.util.TicketDtoConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

@Service
@Transactional
public class TicketServiceImpl implements TicketService {

    @Autowired
    private TicketDao ticketDao;

    @Autowired
    private RouteDao routeDao;

    @Autowired
    private TimetableDao timetableDao;

    @Override
    public void buyTicket(TicketDto ticketDto) throws ActionNotAllowedException {
        AtomicBoolean purchaseIsValid = new AtomicBoolean(true);
        Route route = routeDao.getRouteById(ticketDto.getRouteId());
        List<TimetableItem> timetableItems = timetableDao.getRoutePlanTimetableItems(route.getRoutePlan().getId());
        route.getRoutePlan().setTimetableItems((new HashSet<>(timetableItems)));
        Passenger passenger = PassengerDtoConverter.convertPassengerDtoToEntity(ticketDto.getPassenger());
        route.getTickets().stream().forEach(ticket -> {
            System.out.println(ticket.getPassenger().getFirstName().equals(passenger.getFirstName()));
            System.out.println(ticket.getPassenger().getLastName().equals(passenger.getLastName()));
            if (ticket.getPassenger().getFirstName().equals(passenger.getFirstName())
                    && ticket.getPassenger().getLastName().equals(passenger.getLastName())
                    && ticket.getPassenger().getDateOfBirth().getTime() == passenger.getDateOfBirth().getTime()) {
                purchaseIsValid.set(false);
            }
        });
        if (purchaseIsValid.get() == true) {
            Ticket ticket = TicketDtoConverter.convertTicketDtoToEntity(ticketDto, route, passenger);
            ticketDao.saveTicket(ticket);
        } else throw new ActionNotAllowedException("Passenger already registered on train");
    }

    @Override
    public List<TicketDto> getUserTickets(long userId) {
        List<Ticket> tickets = ticketDao.getUserTickets(userId);
        tickets.forEach(ticket -> {
            List<TimetableItem> items = timetableDao.getRoutePlanTimetableItems(ticket.getRoute().getRoutePlan().getId());
            ticket.getRoute().getRoutePlan().setTimetableItems(new HashSet<>(items));
        });
         return tickets.stream().map(ticket -> TicketDtoConverter.convertTicketEntityToDto(ticket)).sorted().collect(Collectors.toList());
    }

    @Override
    public void deleteTicket(long id) {
        ticketDao.deleteTicket(id);
    }
}
