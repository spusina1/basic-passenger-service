package com.task.basicpassengerservice.repositories;

import com.task.basicpassengerservice.models.Passenger;
import com.task.basicpassengerservice.repositories.filters.PassengerFilterRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PassengerRepository extends JpaRepository<Passenger, Long>, PassengerFilterRepository {
}
