package com.task.basicpassengerservice.controllers;

import com.task.basicpassengerservice.exceptions.ResourceNotFoundException;
import com.task.basicpassengerservice.requests.EditTicketRequest;
import com.task.basicpassengerservice.requests.TicketRequest;
import com.task.basicpassengerservice.responses.Response;
import com.task.basicpassengerservice.responses.TicketResponse;
import com.task.basicpassengerservice.services.TicketService;
import com.task.basicpassengerservice.util.ErrorHandlingHelper;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import java.sql.SQLException;
import java.util.List;

@AllArgsConstructor
@RestController
public class TicketController {

    private final TicketService ticketService;

    @PostMapping("/ticket")
    public ResponseEntity<Response> createTicket(@RequestBody TicketRequest ticketRequest){
        return ResponseEntity.ok(ticketService.addTicket(ticketRequest));
    }

    @GetMapping("/ticket/{id}")
    public ResponseEntity<TicketResponse> showTicket(@PathVariable Long id) {
        return ResponseEntity.ok(ticketService.getTicket(id));
    }

    @GetMapping("/ticket")
    public ResponseEntity<List<TicketResponse>> showTickets() {
        return ResponseEntity.ok(ticketService.getTickets());
    }

    @PutMapping("/ticket")
    public ResponseEntity<Response> updateTicket(@RequestBody EditTicketRequest editTicketRequest) {
        return ResponseEntity.ok(ticketService.putTicket(editTicketRequest));
    }

    @DeleteMapping("/ticket/{id}")
    public ResponseEntity<Response> deleteTicket(@PathVariable Long id) {
        return ResponseEntity.ok(ticketService.deleteTicket(id));
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Response handleConstraintViolationException(ConstraintViolationException exception) {
        return ErrorHandlingHelper.handleConstraintViolationException(exception);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Response handleEntityNotFoundException(ResourceNotFoundException exception) {
        return ErrorHandlingHelper.handleEntityNotFoundException(exception);
    }

    @ExceptionHandler(SQLException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public Response handleIllegalArgumentException(SQLException exception) {
        return ErrorHandlingHelper.handleIllegalArgumentException(exception);
    }
}
