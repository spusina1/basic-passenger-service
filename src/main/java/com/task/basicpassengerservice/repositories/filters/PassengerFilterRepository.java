package com.task.basicpassengerservice.repositories.filters;

import com.task.basicpassengerservice.models.Passenger;
import com.task.basicpassengerservice.requests.PassengerRequest;

import java.util.List;

public interface PassengerFilterRepository {
    List<Passenger> findByFilter(PassengerRequest filter);
}
