package lk.ijse.gdse.ticketservice.controller;

import lk.ijse.gdse.ticketservice.dto.TicketDto;
import lk.ijse.gdse.ticketservice.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/v1/ticket")
public class TicketController {
    @Autowired
    TicketService ticketService;

    @GetMapping("/health")
    public ResponseEntity<String> healthCheck(){
        return new ResponseEntity<>("server is up and running..!!", HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> saveTicket(@RequestBody TicketDto ticketDto){
        try{
            ticketService.registerTicket(ticketDto);
            return new ResponseEntity<>(ticketDto.getTicketId()+" Ticket registered successfully..!!",HttpStatus.CREATED);
        }catch (RuntimeException e){
            return new ResponseEntity<>(ticketDto.getTicketId()+" Ticket Already in the System..!!",HttpStatus.CONFLICT);
        }
    }

    @PutMapping()
    public ResponseEntity<String> updateTicket(@RequestBody TicketDto ticketDto){
        try{
            ticketService.updateTicket(ticketDto);
            return new ResponseEntity<>(ticketDto.getTicketId()+" Ticket updated successfully", HttpStatus.CREATED);
        }catch (RuntimeException e){
            return new ResponseEntity<>(ticketDto.getTicketId()+" Ticket cannot find in the System..!!",HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{ticketId}")
    public ResponseEntity<String> deleteTicket(@PathVariable String ticketId){
        try{
            ticketService.deleteTicket(ticketId);
            return new ResponseEntity<>(ticketId+" Ticket deleted successfully..!!",HttpStatus.OK);
        }catch (RuntimeException e){
            return new ResponseEntity<>(ticketId+" Ticket  cannot find in the System..!!",HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/findByTicketId/{ticketId}")
    public ResponseEntity<?> findByTicketId(@PathVariable String ticketId){
        try{
            return new ResponseEntity<>(ticketService.getTicketByTicketId(ticketId),HttpStatus.OK);
        }catch (RuntimeException e){
            return new ResponseEntity<>(ticketId+" Ticket cannot find in the System..!!",HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/index")
    public ResponseEntity<?> index(){
        try{
            return new ResponseEntity<>(ticketService.getAllTickets(),HttpStatus.OK);
        }catch (RuntimeException e){
            return new ResponseEntity<>("Internal Server Error..!!",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/existsById/{ticketId}")
    public ResponseEntity<Boolean> existsById(@PathVariable String ticketId){
        return new ResponseEntity<>(ticketService.existsById(ticketId),HttpStatus.OK);
    }
}