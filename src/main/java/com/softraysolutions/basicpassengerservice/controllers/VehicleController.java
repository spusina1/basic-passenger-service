package com.softraysolutions.basicpassengerservice.controllers;

import com.softraysolutions.basicpassengerservice.services.VehicleService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
public class VehicleController {

    private final VehicleService vehicleService;
}
