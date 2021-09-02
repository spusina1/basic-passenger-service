package com.softraysolutions.basicpassengerservice.services;

import com.softraysolutions.basicpassengerservice.repositories.PassengerRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class PassengerService {

    private final PassengerRepository passengerRepository;
}
