package com.softraysolutions.basicpassengerservice.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "route")
public class Route {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Size(min = 2, message = "The departure point must be at least 2 characters long.")
    @NotBlank(message = "The departure point cannot be blank.")
    private String departurePoint;

    @NotNull(message = "The departure date cannot be blank.")
    private Date departureDateAndTime;

    @Size(min = 2, message = "The arrival point must be at least 2 characters long.")
    @NotBlank(message = "The arrival point cannot be blank.")
    private String arrivalPoint;

    @NotNull(message = "The arrival date cannot be blank.")
    private Date arrivalDateAndTime;

    @OneToMany(mappedBy = "route", cascade = CascadeType.ALL)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<Ticket> tickets = new ArrayList<>();

    @ManyToOne
    @JsonIgnore
    private Company company;

    public Route(String departurePoint, Date departureDateAndTime, String arrivalPoint, Date arrivalDateAndTime, Company company) {
        this.departurePoint = departurePoint;
        this.departureDateAndTime = departureDateAndTime;
        this.arrivalPoint = arrivalPoint;
        this.arrivalDateAndTime = arrivalDateAndTime;
        this.company = company;
    }
}
