package com.softraysolutions.basicpassengerservice.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EditPassengerRequest {
    private Long id;
    private String firstName;
    private String lastName;
    private String passportId;
    private String address;
    private String phoneNumber;
    private String email;
}
