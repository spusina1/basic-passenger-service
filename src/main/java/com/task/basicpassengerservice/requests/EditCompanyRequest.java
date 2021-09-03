package com.task.basicpassengerservice.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EditCompanyRequest {
    private Long id;
    private String name;
    private String address;
    private String phoneNumber;
    private String email;
}
