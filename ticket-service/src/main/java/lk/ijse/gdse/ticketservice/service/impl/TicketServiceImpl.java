package lk.ijse.gdse.ticketservice.service.impl;

import lk.ijse.gdse.ticketservice.dto.TicketDto;
import lk.ijse.gdse.ticketservice.repo.TicketRepo;
import lk.ijse.gdse.ticketservice.service.TicketService;
import lk.ijse.gdse.ticketservice.utils.Convertor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class TicketServiceImpl implements TicketService {
    @Autowired
    TicketRepo ticketRepo;
    @Autowired
    Convertor convertor;


    @Override
    public void registerTicket(TicketDto ticketDto) {
        if(ticketRepo.existsById(ticketDto.getTicketId()))
            throw new RuntimeException("already in the system");
        ticketRepo.save(convertor.ticketDtoToEntity(ticketDto));
    }

    @Override
    public void updateTicket(TicketDto ticketDto) {
        if(!ticketRepo.existsById(ticketDto.getTicketId()))
            throw new RuntimeException("cannot find in the system");
        ticketRepo.save(convertor.ticketDtoToEntity(ticketDto));
    }

    @Override
    public void deleteTicket(String ticketId) {
        if(!ticketRepo.existsById(ticketId))
            throw new RuntimeException("cannot find in the system");
        ticketRepo.deleteById(ticketId);
    }

    @Override
    public TicketDto getTicketByTicketId(String ticketId) {
        if(!ticketRepo.existsById(ticketId))
            throw new RuntimeException("cannot find in the system");
        return convertor.ticketToDto(ticketRepo.findById(ticketId).get());
    }

    @Override
    public List<TicketDto> getAllTickets() {
        return convertor.ticketEntityListToTicketDTOList(ticketRepo.findAll());
    }

    @Override
    public boolean existsById(String ticketId) {
        return ticketRepo.existsById(ticketId);
    }
}