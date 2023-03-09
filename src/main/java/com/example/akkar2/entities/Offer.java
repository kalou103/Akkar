package com.example.akkar2.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Offer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date dateTransportation;

    //private String startLocation; offer client (driver)
    //private String endLocation;offer client (driver)
    private double price;
    private LocalDateTime dateTime;
    @Enumerated(EnumType.STRING)
    private  TransportationArea transportationArea;
    @Enumerated(EnumType.STRING)
    private TransportationType transportationtype;
    @Enumerated(EnumType.STRING)
    private DriverLocation driverLocation;
    // New fields for Linear Regression
    @Transient
    //transportationArea
    private String deliveryCity;
    @Transient

    private String pickupCity;
    @Transient
    private String vehicleType;
    @Transient
    private double distance;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "driver_id")
    @JsonIgnore
    private Driver driver;

   // @ManyToOne
  //  private TransportationDemand transportationDemand;

    // Constructor, getters, and setters


}
