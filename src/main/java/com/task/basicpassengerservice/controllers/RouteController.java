package com.task.basicpassengerservice.controllers;

import com.task.basicpassengerservice.exceptions.ResourceNotFoundException;
import com.task.basicpassengerservice.models.Route;
import com.task.basicpassengerservice.requests.EditRouteRequest;
import com.task.basicpassengerservice.requests.RouteRequest;
import com.task.basicpassengerservice.responses.Response;
import com.task.basicpassengerservice.services.RouteService;
import com.task.basicpassengerservice.util.ErrorHandlingHelper;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

@AllArgsConstructor
@RestController
public class RouteController {

    private final RouteService routeService;

    @PostMapping("/route")
    public ResponseEntity<Response> createRoute(@RequestBody RouteRequest routeRequest) throws ParseException {
        return ResponseEntity.ok(routeService.addRoute(routeRequest));
    }

    @GetMapping("/route/{id}")
    public ResponseEntity<Route> showRoute(@PathVariable Long id) {
        return ResponseEntity.ok(routeService.getRoute(id));
    }

    @GetMapping("/route")
    public ResponseEntity<List<Route>> showRoutes() {
        return ResponseEntity.ok(routeService.getRoutes());
    }

    @PutMapping("/route")
    public ResponseEntity<Response> updateRoute(@RequestBody EditRouteRequest editRouteRequest) throws ParseException {
        return ResponseEntity.ok(routeService.putRoute(editRouteRequest));
    }

    @DeleteMapping("/route/{id}")
    public ResponseEntity<Response> deleteRoute(@PathVariable Long id) {
        return ResponseEntity.ok(routeService.deleteRoute(id));
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

    @ExceptionHandler(ParseException.class)
    @ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
    public Response handleParseException(ParseException exception) {
        return ErrorHandlingHelper.handleParseException(exception);
    }

    @ExceptionHandler(NumberFormatException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Response handleNumberFormatException(NumberFormatException exception) {
        return ErrorHandlingHelper.handleNumberFormatException(exception);
    }
}
