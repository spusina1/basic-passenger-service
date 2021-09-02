package com.softraysolutions.basicpassengerservice.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "ticket")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "The travel class cannot be blank.")
    private String travelClass;

    @NotBlank(message = "The seat cannot be blank.")
    private Integer seat;

    @ManyToOne
    @JsonIgnore
    private Passenger passenger;

    @ManyToOne
    @JsonIgnore
    private Route route;

    @ManyToOne
    @JsonIgnore
    private Vehicle vehicle;
}
