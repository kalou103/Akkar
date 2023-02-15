package com.example.akkar2.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.List;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode

public class Driver implements Serializable {

    // Basic For All users
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idDriver")
    private int idDriver;

    @Column(nullable = false)
    private int phoneNumber;

    @Column(nullable = true)
    private float driverPrice;

    @Column(nullable = true)
    private String driverLocation;

    @Column(nullable = true)
    private String truckType;

    @Column(nullable = true)
    private double cabinCapacity;


    //TransportationDemand
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "driver")
    private List<TransportationDemand> transportationDemand;

   /*
    //offers
    @JsonIgnore
    @ManyToMany
    private List<Offer> offers;*/
    //offre bech tetnaha
}