package com.softraysolutions.basicpassengerservice.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompanyRequest {
    private String name;
    private String address;
    private String phoneNumber;
    private String email;
}
