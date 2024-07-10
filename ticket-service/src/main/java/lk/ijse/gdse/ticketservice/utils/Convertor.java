package lk.ijse.gdse.ticketservice.utils;


import lk.ijse.gdse.ticketservice.dto.TicketDto;
import lk.ijse.gdse.ticketservice.entity.Ticket;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class Convertor {
    @Autowired
    ModelMapper modelMapper;

    public Ticket ticketDtoToEntity(TicketDto ticketDto){
        return modelMapper.map(ticketDto, Ticket.class);
    }
    public TicketDto ticketToDto(Ticket ticket) {
        return modelMapper.map(ticket, TicketDto.class);
    }
    public List<TicketDto> ticketEntityListToTicketDTOList(List<Ticket> users){
        return modelMapper.map(users,new TypeToken<List<TicketDto>>(){}.getType());
    }
}
