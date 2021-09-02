package com.softraysolutions.basicpassengerservice.repositories;

import com.softraysolutions.basicpassengerservice.models.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
}
