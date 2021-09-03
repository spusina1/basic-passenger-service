package com.softraysolutions.basicpassengerservice.repositories.filters;

import com.softraysolutions.basicpassengerservice.models.Company;
import com.softraysolutions.basicpassengerservice.models.Passenger;
import com.softraysolutions.basicpassengerservice.requests.CompanyRequest;
import com.softraysolutions.basicpassengerservice.requests.PassengerRequest;

import java.util.List;

public interface CompanyFilterRepository {
    List<Company> findByFilter(CompanyRequest filter);
}
