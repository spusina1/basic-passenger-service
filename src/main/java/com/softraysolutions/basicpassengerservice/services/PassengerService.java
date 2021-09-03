package com.softraysolutions.basicpassengerservice.services;

import com.softraysolutions.basicpassengerservice.exceptions.ResourceNotFoundException;
import com.softraysolutions.basicpassengerservice.models.Passenger;
import com.softraysolutions.basicpassengerservice.repositories.PassengerRepository;
import com.softraysolutions.basicpassengerservice.requests.EditPassengerRequest;
import com.softraysolutions.basicpassengerservice.requests.PassengerRequest;
import com.softraysolutions.basicpassengerservice.responses.PassengerResponse;
import com.softraysolutions.basicpassengerservice.responses.Response;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
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

    public Passenger getPassenger(Long id) {
        return passengerRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Passenger with Id=" + id + " does not exist."));
    }

    public List<Passenger> getPassengers(PassengerRequest filterRequest) {
        List<Passenger> passengers = passengerRepository.findByFilter(filterRequest);
        if (passengers.size() == 0) throw new ResourceNotFoundException("No passengers found.");
        return passengers;
    }

    public Response putPassenger(EditPassengerRequest editPassengerRequest) {
        Optional<Passenger> passenger = passengerRepository.findById(editPassengerRequest.getId());
        if (!passenger.isPresent()) return new Response("Passenger with Id=" + editPassengerRequest.getId() + " does not exist.", 400);

        passenger.get().setFirstName(editPassengerRequest.getFirstName());
        passenger.get().setLastName(editPassengerRequest.getLastName());
        passenger.get().setPassportId(editPassengerRequest.getPassportId());
        passenger.get().setAddress(editPassengerRequest.getAddress());
        passenger.get().setPhoneNumber(editPassengerRequest.getPhoneNumber());
        passenger.get().setEmail(editPassengerRequest.getEmail());

        passengerRepository.save(passenger.get());

        return new Response("Passenger successfully updated.", 200);
    }

    public Response deletePassenger(Long id) {
        Optional<Passenger> passenger = passengerRepository.findById(id);
        if (!passenger.isPresent()) return new Response("Passenger with Id=" + id + " does not exist.", 400);

        passengerRepository.delete(passenger.get());
        return new Response("Passenger successfully deleted.", 200);
    }
}
