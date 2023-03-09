package com.example.akkar2.entities;

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
@Table(name = "MembershipPayement")
public class Membership implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idmembership")
    private int id;
    private int idclient;
    private int amount;
    private LocalDate PayedAt=LocalDate.now();

}
