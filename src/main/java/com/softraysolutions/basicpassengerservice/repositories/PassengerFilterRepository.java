package com.softraysolutions.basicpassengerservice.repositories;

import com.softraysolutions.basicpassengerservice.models.Passenger;
import com.softraysolutions.basicpassengerservice.requests.PassengerRequest;

import java.util.List;

public interface PassengerFilterRepository {
    List<Passenger> findByFilter(PassengerRequest filter);
}
