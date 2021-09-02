package com.softraysolutions.basicpassengerservice.controllers;

import com.softraysolutions.basicpassengerservice.requests.PassengerRequest;
import com.softraysolutions.basicpassengerservice.responses.PassengerResponse;
import com.softraysolutions.basicpassengerservice.responses.Response;
import com.softraysolutions.basicpassengerservice.services.PassengerService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
public class PassengerController {

    private final PassengerService passengerService;

    @PostMapping("/passenger")
    public ResponseEntity<Response> createPassenger(@RequestBody PassengerRequest passengerRequest) {
        Response response = passengerService.addPassenger(passengerRequest);
        return ResponseEntity.ok(response);
    }

}
