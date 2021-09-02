package com.softraysolutions.basicpassengerservice.controllers;

import com.softraysolutions.basicpassengerservice.services.CompanyService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
public class CompanyController {

    private final CompanyService companyService;
}
