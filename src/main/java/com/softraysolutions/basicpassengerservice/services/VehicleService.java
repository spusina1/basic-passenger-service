package com.softraysolutions.basicpassengerservice.services;

import com.softraysolutions.basicpassengerservice.repositories.VehicleRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class VehicleService {

    private final VehicleRepository vehicleRepository;
}
