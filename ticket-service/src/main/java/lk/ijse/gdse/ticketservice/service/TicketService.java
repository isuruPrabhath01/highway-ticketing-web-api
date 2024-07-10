package lk.ijse.gdse.ticketservice.service;

import lk.ijse.gdse.ticketservice.dto.TicketDto;

import java.util.List;

public interface TicketService {
    void registerTicket (TicketDto ticketDto);
    void updateTicket (TicketDto ticketDto);
    void deleteTicket (String ticketId);
    TicketDto getTicketByTicketId (String ticketId);
    List<TicketDto> getAllTickets ();
    boolean existsById(String ticketId);
}
