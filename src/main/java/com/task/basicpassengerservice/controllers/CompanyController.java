package com.task.basicpassengerservice.controllers;

import com.task.basicpassengerservice.exceptions.ResourceNotFoundException;
import com.task.basicpassengerservice.models.Company;
import com.task.basicpassengerservice.requests.CompanyRequest;
import com.task.basicpassengerservice.requests.EditCompanyRequest;
import com.task.basicpassengerservice.responses.Response;
import com.task.basicpassengerservice.services.CompanyService;
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
public class CompanyController {

    private final CompanyService companyService;

    @PostMapping("/company")
    public ResponseEntity<Response> createCompany(@RequestBody CompanyRequest companyRequest) {
        return ResponseEntity.ok(companyService.addCompany(companyRequest));
    }

    @GetMapping("/company/{id}")
    public ResponseEntity<Company> showCompany(@PathVariable Long id) {
        return ResponseEntity.ok(companyService.getCompany(id));
    }

    @GetMapping("/company")
    public ResponseEntity<List<Company>> showCompanies(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String address,
            @RequestParam(required = false) String phoneNumber,
            @RequestParam(required = false) String email
    ) {
        CompanyRequest filterRequest = new CompanyRequest(
                name, address, phoneNumber, email
        );

        return ResponseEntity.ok(companyService.getCompanies(filterRequest));
    }

    @PutMapping("/company")
    public ResponseEntity<Response> updateCompany(@RequestBody EditCompanyRequest editCompanyRequest) {
        return ResponseEntity.ok(companyService.putCompany(editCompanyRequest));
    }

    @DeleteMapping("/company/{id}")
    public ResponseEntity<Response> deleteCompany(@PathVariable Long id) {
        return ResponseEntity.ok(companyService.deleteCompany(id));
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
