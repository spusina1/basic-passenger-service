package com.softraysolutions.basicpassengerservice.controllers;

import com.softraysolutions.basicpassengerservice.exceptions.ResourceNotFoundException;
import com.softraysolutions.basicpassengerservice.models.Passenger;
import com.softraysolutions.basicpassengerservice.requests.CompanyRequest;
import com.softraysolutions.basicpassengerservice.requests.EditPassengerRequest;
import com.softraysolutions.basicpassengerservice.requests.PassengerRequest;
import com.softraysolutions.basicpassengerservice.responses.Response;
import com.softraysolutions.basicpassengerservice.services.CompanyService;
import com.softraysolutions.basicpassengerservice.util.ErrorHandlingHelper;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import java.sql.SQLException;
import java.util.List;

@AllArgsConstructor
@RestController
public class CompanyController {

    private final CompanyService companyService;

    @PostMapping("/company")
    public ResponseEntity<Response> createCompany(@RequestBody CompanyRequest companyRequest) {
        return ResponseEntity.ok(companyService.addCompany(companyRequest));
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
