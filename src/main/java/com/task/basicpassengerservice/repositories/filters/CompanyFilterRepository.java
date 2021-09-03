package com.task.basicpassengerservice.repositories.filters;

import com.task.basicpassengerservice.models.Company;
import com.task.basicpassengerservice.requests.CompanyRequest;

import java.util.List;

public interface CompanyFilterRepository {
    List<Company> findByFilter(CompanyRequest filter);
}
