package com.task.basicpassengerservice.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TicketRequest {
    private String travelClass;
    private Integer seat;
    private Long passengerId;
    private Long routeId;
}
