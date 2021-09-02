package com.softraysolutions.basicpassengerservice.controllers;

import com.softraysolutions.basicpassengerservice.services.RouteService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
public class RouteController {

    private final RouteService routeService;
}
