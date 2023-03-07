package com.example.akkar2.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@DiscriminatorValue("D")

public class Driver extends User implements Serializable {

    // Basic For All users

    @Column(nullable = false)
    private int phoneNumber;

   // @Column(nullable = true)
   // private float driverPrice;


    @Enumerated(EnumType.STRING)
    private DriverLocation driverLocation;

    @Column(nullable = true)
    private String truckType;

    @Column(nullable = true)
    private double cabinCapacity;

    @Column(nullable = true)
    private Date date;

  //  @Column(nullable = true)
  //  private double price;


    //TransportationDemand
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "driver")
    private List<TransportationDemand> transportationDemand;


}