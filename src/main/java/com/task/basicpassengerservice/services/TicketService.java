package com.task.basicpassengerservice.services;

import com.task.basicpassengerservice.exceptions.ResourceNotFoundException;
import com.task.basicpassengerservice.models.Passenger;
import com.task.basicpassengerservice.models.Route;
import com.task.basicpassengerservice.models.Ticket;
import com.task.basicpassengerservice.repositories.TicketRepository;
import com.task.basicpassengerservice.requests.EditTicketRequest;
import com.task.basicpassengerservice.requests.TicketRequest;
import com.task.basicpassengerservice.responses.Response;
import com.task.basicpassengerservice.responses.TicketResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class TicketService {

    private final TicketRepository ticketRepository;

    private final PassengerService passengerService;
    private final RouteService routeService;

    public Response addTicket(TicketRequest ticketRequest) {
        Passenger passenger = passengerService.getPassenger(ticketRequest.getPassengerId());
        Route route = routeService.getRoute(ticketRequest.getRouteId());

        Ticket ticket = new Ticket(ticketRequest.getTravelClass(), ticketRequest.getSeat(), passenger, route);
        ticketRepository.save(ticket);

        return new Response("Ticket is saved.", 200);
    }

    public TicketResponse getTicket(Long id) {
        return ticketRepository
                .findById(id)
                .map(ticket -> new TicketResponse(ticket.getId(), ticket.getTravelClass(), ticket.getSeat(),
                        ticket.getPassenger().getId(), ticket.getRoute().getId()))
                .orElseThrow(() -> new ResourceNotFoundException("Ticket with Id=" + id + " does not exist."));
    }

    public List<TicketResponse> getTickets() {
        List<TicketResponse> list = ticketRepository
                .findAll()
                .stream()
                .map(ticket -> new TicketResponse(ticket.getId(), ticket.getTravelClass(), ticket.getSeat(),
                        ticket.getPassenger().getId(), ticket.getRoute().getId()))
                .collect(Collectors.toList());
        if(list.isEmpty()) throw new ResourceNotFoundException("No tickets found.");
        return list;
    }

    public Response putTicket(EditTicketRequest editTicketRequest) {
        Optional<Ticket> ticket = ticketRepository.findById(editTicketRequest.getId());
        if (!ticket.isPresent()) return new Response("Ticket with Id=" + editTicketRequest.getId() + " does not exist.", 400);

        ticket.get().setTravelClass(editTicketRequest.getTravelClass());
        ticket.get().setSeat(editTicketRequest.getSeat());

        ticketRepository.save(ticket.get());

        return new Response("Ticket successfully updated.", 200);
    }

    public Response deleteTicket(Long id) {
        Optional<Ticket> ticket = ticketRepository.findById(id);
        if (!ticket.isPresent()) return new Response("Ticket with Id=" + id + " does not exist.", 400);

        ticketRepository.delete(ticket.get());
        return new Response("Ticket successfully deleted.", 200);
    }
}
