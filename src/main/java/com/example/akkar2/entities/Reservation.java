package com.example.akkar2.entities;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
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
    private Long idRes;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private boolean status;
    private Double prepaymentamount;
    private boolean prepaymentstatus;
    private Double totalamount;
    private int numberofresidents;
    @ManyToOne
    @JsonIgnore
    private Client client;
    @ManyToOne
    @JsonIgnore
    private RealEstate realEstate;
}
