package com.softraysolutions.basicpassengerservice.services;

import com.softraysolutions.basicpassengerservice.exceptions.ResourceNotFoundException;
import com.softraysolutions.basicpassengerservice.models.Company;
import com.softraysolutions.basicpassengerservice.models.Passenger;
import com.softraysolutions.basicpassengerservice.repositories.CompanyRepository;
import com.softraysolutions.basicpassengerservice.requests.CompanyRequest;
import com.softraysolutions.basicpassengerservice.responses.CompanyResponse;
import com.softraysolutions.basicpassengerservice.responses.Response;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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


    public CompanyResponse getCompany(Long id) {
        return companyRepository
                .findById(id)
                .map(company -> new CompanyResponse(company.getId(), company.getName(), company.getAddress(), company.getPhoneNumber(),company.getEmail()))
                .orElseThrow(() -> new ResourceNotFoundException("Company with Id=" + id + " does not exist."));
    }

    public List<Company> getCompanies(CompanyRequest filterRequest) {
        List<Company> companies = companyRepository.findByFilter(filterRequest);
        if (companies.size() == 0) throw new ResourceNotFoundException("No companies found.");
        return companies;
    }
}
