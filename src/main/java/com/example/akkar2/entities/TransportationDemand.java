package com.example.akkar2.entities;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode

public class TransportationDemand implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idTransportationDemand")
    private Long idTransportationDemand;
    private Date dateTransportation;
    private String departure ;
    private String destination ;
    private double capacityNeeded ;
    private String description ;
    @Enumerated(EnumType.STRING)
    private  TransportationArea transportationArea;
    @Enumerated(EnumType.STRING)
    private TransportationType transportationtype;

    // New fields for Linear Regression
    private double weight;
    private double height;
    private double width;
    private String deliveryCity;
    private String pickupCity;
    private String vehicleType;
    private double distance;

    private double price;

    private Date pickupDate;
    @ManyToOne
    @JsonIgnore
    private Driver driver; //driver twali mech User

    @ManyToOne
    @JsonIgnore
    Client client;

  /* @OneToMany(mappedBy = "transportationDemand")
    private List<Offer> offers;*/



}
