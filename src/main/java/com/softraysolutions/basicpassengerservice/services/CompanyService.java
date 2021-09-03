package com.softraysolutions.basicpassengerservice.services;

import com.softraysolutions.basicpassengerservice.exceptions.ResourceNotFoundException;
import com.softraysolutions.basicpassengerservice.models.Company;
import com.softraysolutions.basicpassengerservice.models.Passenger;
import com.softraysolutions.basicpassengerservice.repositories.CompanyRepository;
import com.softraysolutions.basicpassengerservice.requests.CompanyRequest;
import com.softraysolutions.basicpassengerservice.requests.EditCompanyRequest;
import com.softraysolutions.basicpassengerservice.requests.EditPassengerRequest;
import com.softraysolutions.basicpassengerservice.responses.CompanyResponse;
import com.softraysolutions.basicpassengerservice.responses.Response;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public Response putCompany(EditCompanyRequest editCompanyRequest) {
        Optional<Company> company = companyRepository.findById(editCompanyRequest.getId());
        if (!company.isPresent()) return new Response("Company with Id=" + editCompanyRequest.getId() + " does not exist.", 400);

        company.get().setName(editCompanyRequest.getName());
        company.get().setAddress(editCompanyRequest.getAddress());
        company.get().setPhoneNumber(editCompanyRequest.getPhoneNumber());
        company.get().setEmail(editCompanyRequest.getEmail());

        companyRepository.save(company.get());

        return new Response("Company successfully updated.", 200);
    }

    public Response deleteCompany(Long id) {
        Optional<Company> company = companyRepository.findById(id);
        if (!company.isPresent()) return new Response("Company with Id=" + id + " does not exist.", 400);

        companyRepository.delete(company.get());
        return new Response("Company successfully deleted.", 200);
    }
}
