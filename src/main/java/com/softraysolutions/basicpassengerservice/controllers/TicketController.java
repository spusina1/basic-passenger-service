package com.softraysolutions.basicpassengerservice.controllers;

import com.softraysolutions.basicpassengerservice.services.TicketService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
public class TicketController {

    private final TicketService ticketService;
}
