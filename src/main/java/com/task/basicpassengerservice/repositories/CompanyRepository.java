package com.task.basicpassengerservice.repositories;

import com.task.basicpassengerservice.models.Company;
import com.task.basicpassengerservice.repositories.filters.CompanyFilterRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long>, CompanyFilterRepository {
}
