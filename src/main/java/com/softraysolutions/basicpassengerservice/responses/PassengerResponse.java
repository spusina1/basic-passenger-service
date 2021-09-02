package com.softraysolutions.basicpassengerservice.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PassengerResponse {
    private String firstName;
    private String lastName;
    private String passportId;
    private String address;
    private String phoneNumber;
    private String email;
}
