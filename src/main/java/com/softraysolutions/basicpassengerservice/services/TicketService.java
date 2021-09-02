package com.softraysolutions.basicpassengerservice.services;

import com.softraysolutions.basicpassengerservice.repositories.TicketRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class TicketService {

    private final TicketRepository ticketRepository;
}
