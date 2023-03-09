package com.example.akkar2.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


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

    @ManyToOne
    private Driver driver; //driver twali mech User

    @ManyToOne
    Client client;
   /* @OneToMany(mappedBy = "transportationdemand")
    private List<Offer> offers; */
    //offre bech tetnaha

}
