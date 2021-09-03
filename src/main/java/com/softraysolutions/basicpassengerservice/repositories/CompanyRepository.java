package com.softraysolutions.basicpassengerservice.repositories;

import com.softraysolutions.basicpassengerservice.models.Company;
import com.softraysolutions.basicpassengerservice.repositories.filters.CompanyFilterRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long>, CompanyFilterRepository {
}
