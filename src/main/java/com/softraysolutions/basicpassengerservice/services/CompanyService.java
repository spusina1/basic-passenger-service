package com.softraysolutions.basicpassengerservice.services;

import com.softraysolutions.basicpassengerservice.models.Company;
import com.softraysolutions.basicpassengerservice.models.Passenger;
import com.softraysolutions.basicpassengerservice.repositories.CompanyRepository;
import com.softraysolutions.basicpassengerservice.requests.CompanyRequest;
import com.softraysolutions.basicpassengerservice.responses.Response;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class CompanyService {

    private final CompanyRepository companyRepository;

    public Response addCompany(CompanyRequest companyRequest) {
        companyRepository.save(
                new Company(
                        companyRequest.getName(),
                        companyRequest.getAddress(),
                        companyRequest.getPhoneNumber(),
                        companyRequest.getEmail()
                ));
        return new Response("Company is saved.", 200);
    }


}
