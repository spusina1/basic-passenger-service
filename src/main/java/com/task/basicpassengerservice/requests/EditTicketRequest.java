package com.task.basicpassengerservice.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EditTicketRequest {
    private Long id;
    private String travelClass;
    private Integer seat;
}
