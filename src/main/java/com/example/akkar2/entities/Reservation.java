package com.example.akkar2.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Reservation implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_res")
    private Long idRes;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    @JsonIgnore
    private boolean status;
    @JsonIgnore
    private Double prepaymentamount;
    @JsonIgnore
    private boolean prepaymentstatus;
    @JsonIgnore
    private Double totalamount;

    private int numberofresidents;
    @ManyToOne
    @JsonIgnore
    private Client client;
    @ManyToOne
    @JsonIgnore
    private RealEstate realEstate;
}
