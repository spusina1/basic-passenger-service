package com.softraysolutions.basicpassengerservice.services;

import com.softraysolutions.basicpassengerservice.repositories.RouteRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class RouteService {

    private final RouteRepository routeRepository;
}