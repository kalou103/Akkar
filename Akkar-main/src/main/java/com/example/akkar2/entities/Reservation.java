package com.example.akkar2.entities;

import javax.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Reservation implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idreservation")
    private Long idRes;
    @Temporal(TemporalType.DATE)
    private Date checkInDate;
    @Temporal(TemporalType.DATE)
    private Date checkOutDate;
    private boolean status;
    private double prepaymentamount;
    private boolean prepaymentstatus;
    private double totalamount;
    private int numberofresidents;
    @ManyToOne
    private Client client;
    @ManyToOne
    private RealEstate realEstate;
}
