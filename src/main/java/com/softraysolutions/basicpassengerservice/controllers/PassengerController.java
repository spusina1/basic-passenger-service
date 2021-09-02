package com.softraysolutions.basicpassengerservice.controllers;

import com.softraysolutions.basicpassengerservice.services.PassengerService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
public class PassengerController {

    private final PassengerService passengerService;
}
