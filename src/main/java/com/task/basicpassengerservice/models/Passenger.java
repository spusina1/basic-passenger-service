package com.task.basicpassengerservice.models;

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
@Table(name = "passenger", uniqueConstraints = {
        @UniqueConstraint(columnNames = {
                "passportId"
        }),
        @UniqueConstraint(columnNames = {
                "phoneNumber"
        }),
        @UniqueConstraint(columnNames = {
                "email"
        })})
public class Passenger {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Size(min = 2, message = "The first name must be at least 2 characters long.")
    @NotBlank(message = "The first name cannot be blank.")
    private String firstName;

    @Size(min = 2, message = "The last name must be at least 2 characters long.")
    @NotBlank(message = "The last name cannot be blank.")
    private String lastName;

    @Size(min = 6, max = 9, message = "The passportId must be between 6 and 9 characters long.")
    @NotBlank(message = "The passportId cannot be blank.")
    private String passportId;

    @NotBlank(message = "The address cannot be blank.")
    private String address;

    @Size(min = 9, max = 15, message = "The phone number must be between 9 and 15 digits long.")
    @NotBlank(message = "The phone number cannot be blank.")
    private String phoneNumber;

    @Email(message = "Email must be a valid email address.")
    @NotBlank(message = "The email address cannot be blank.")
    private String email;

    @OneToMany(mappedBy = "passenger", cascade = CascadeType.ALL)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<Ticket> tickets = new ArrayList<>();

    public Passenger(String firstName, String lastName, String passportId, String address, String phoneNumber, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.passportId = passportId;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }
}
