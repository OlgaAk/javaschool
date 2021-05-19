package io.github.olgaak.util;

import io.github.olgaak.dto.TicketDto;

public class TicketValidator {
    public static boolean checkIfCanBuyTicket(TicketDto ticketDto) {
        //todo check if no same passenger on train
        //todo check if more than 10 min left
        return true;
    }
}
