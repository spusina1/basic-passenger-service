package com.softraysolutions.basicpassengerservice.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "company", uniqueConstraints = {
        @UniqueConstraint(columnNames = {
                "phoneNumber"
        }),
        @UniqueConstraint(columnNames = {
                "email"
        })})
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Size(min = 2, message = "The name must be at least 2 characters long.")
    @NotBlank(message = "The name cannot be blank.")
    private String name;

    @NotBlank(message = "The address cannot be blank.")
    private String address;

    @Size(min = 9, max = 15, message = "The phone number must be between 9 and 15 digits long.")
    @NotBlank(message = "The phone number cannot be blank.")
    private String phoneNumber;

    @Email(message = "Email must be a valid email address.")
    @NotBlank(message = "The email address cannot be blank.")
    private String email;

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<Vehicle> vehicles = new ArrayList<>();

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<Route> routes = new ArrayList<>();
}
