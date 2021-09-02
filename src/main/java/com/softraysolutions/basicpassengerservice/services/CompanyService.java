package com.softraysolutions.basicpassengerservice.services;

import com.softraysolutions.basicpassengerservice.repositories.CompanyRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class CompanyService {

    private final CompanyRepository companyRepository;
}
