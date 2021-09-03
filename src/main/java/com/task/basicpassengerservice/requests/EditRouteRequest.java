package com.task.basicpassengerservice.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EditRouteRequest {
    private Long id;
    private String departurePoint;
    private String departureDate;
    private String departureTime;
    private String arrivalPoint;
    private String arrivalDate;
    private String arrivalTime;
}
