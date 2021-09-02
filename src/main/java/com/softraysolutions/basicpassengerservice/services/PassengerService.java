package com.softraysolutions.basicpassengerservice.services;

import com.softraysolutions.basicpassengerservice.exceptions.ResourceNotFoundException;
import com.softraysolutions.basicpassengerservice.models.Passenger;
import com.softraysolutions.basicpassengerservice.repositories.PassengerRepository;
import com.softraysolutions.basicpassengerservice.requests.PassengerRequest;
import com.softraysolutions.basicpassengerservice.responses.PassengerResponse;
import com.softraysolutions.basicpassengerservice.responses.Response;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class PassengerService {

    private final PassengerRepository passengerRepository;

    public Response addPassenger(PassengerRequest passengerRequest) {

        passengerRepository.save(
                new Passenger(
                passengerRequest.getFirstName(),
                passengerRequest.getLastName(),
                passengerRequest.getPassportId(),
                passengerRequest.getAddress(),
                passengerRequest.getPhoneNumber(),
                passengerRequest.getEmail()
        ));
        return new Response("Passenger is saved.", 200);
    }

}
