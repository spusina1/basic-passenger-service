package com.softraysolutions.basicpassengerservice.controllers;

import com.softraysolutions.basicpassengerservice.exceptions.ResourceNotFoundException;
import com.softraysolutions.basicpassengerservice.requests.PassengerRequest;
import com.softraysolutions.basicpassengerservice.requests.RouteRequest;
import com.softraysolutions.basicpassengerservice.responses.Response;
import com.softraysolutions.basicpassengerservice.services.RouteService;
import com.softraysolutions.basicpassengerservice.util.ErrorHandlingHelper;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import java.sql.SQLException;
import java.text.ParseException;

@AllArgsConstructor
@RestController
public class RouteController {

    private final RouteService routeService;

    @PostMapping("/route")
    public ResponseEntity<Response> createRoute(@RequestBody RouteRequest routeRequest) throws ParseException {
        return ResponseEntity.ok(routeService.addRoute(routeRequest));
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
