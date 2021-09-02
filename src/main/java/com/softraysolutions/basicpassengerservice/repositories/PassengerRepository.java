package com.softraysolutions.basicpassengerservice.repositories;

import com.softraysolutions.basicpassengerservice.models.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PassengerRepository extends JpaRepository<Passenger, Long> {
    Optional<Passenger> findByPassportId(String passportId);
}
