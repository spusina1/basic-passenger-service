package com.task.basicpassengerservice.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TicketResponse {
    private Long id;
    private String travelClass;
    private Integer seat;
    private Long passengerId;
    private Long routeId;
}
