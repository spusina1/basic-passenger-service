package com.softraysolutions.basicpassengerservice.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "vehicle")
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Size(min = 2, message = "The type must be at least 2 characters long.")
    @NotBlank(message = "The type cannot be blank.")
    private String type;

    @Size(min = 2, message = "The maximum passenger be at least 2.")
    @NotBlank(message = "The maximum passenger cannot be blank.")
    private Integer maximumPassenger;

    @OneToMany(mappedBy = "vehicle", cascade = CascadeType.ALL)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<Ticket> tickets = new ArrayList<>();

    @ManyToOne
    @JsonIgnore
    private Company company;
}
