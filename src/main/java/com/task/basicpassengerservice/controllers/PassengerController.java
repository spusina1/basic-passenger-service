package com.task.basicpassengerservice.controllers;

import com.task.basicpassengerservice.exceptions.ResourceNotFoundException;
import com.task.basicpassengerservice.models.Passenger;
import com.task.basicpassengerservice.requests.EditPassengerRequest;
import com.task.basicpassengerservice.requests.PassengerRequest;
import com.task.basicpassengerservice.responses.Response;
import com.task.basicpassengerservice.services.PassengerService;
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
public class PassengerController {

    private final PassengerService passengerService;

    @PostMapping("/passenger")
    public ResponseEntity<Response> createPassenger(@RequestBody PassengerRequest passengerRequest) {
        return ResponseEntity.ok(passengerService.addPassenger(passengerRequest));
    }

    @GetMapping("/passenger/{id}")
    public ResponseEntity<Passenger> showPassenger(@PathVariable Long id) {
        return ResponseEntity.ok(passengerService.getPassenger(id));
    }

    @GetMapping("/passenger")
    public ResponseEntity<List<Passenger>> showPassengers(
            @RequestParam(required = false) String firstName,
            @RequestParam(required = false) String lastName,
            @RequestParam(required = false) String passportId,
            @RequestParam(required = false) String address,
            @RequestParam(required = false) String phoneNumber,
            @RequestParam(required = false) String email
    ) {
        PassengerRequest filterRequest = new PassengerRequest(
                firstName, lastName, passportId, address, phoneNumber, email
        );

        return ResponseEntity.ok(passengerService.getPassengers(filterRequest));
    }

    @PutMapping("/passenger")
    public ResponseEntity<Response> updatePassenger(@RequestBody EditPassengerRequest editPassengerRequest) {
        return ResponseEntity.ok(passengerService.putPassenger(editPassengerRequest));
    }

    @DeleteMapping("/passenger/{id}")
    public ResponseEntity<Response> deletePassenger(@PathVariable Long id) {
        return ResponseEntity.ok(passengerService.deletePassenger(id));
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
