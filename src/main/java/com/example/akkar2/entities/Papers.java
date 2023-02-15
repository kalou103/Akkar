package com.example.akkar2.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Papers implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idpapers")
    private Long id;
    @Lob
    private byte[] loanPapers;
    @Lob
    private byte[] certificateOfOwnership;
    @Lob
    private byte[] realEstatesFees;
    @Lob
    private byte[] authentificationCerfification;
    @OneToOne(mappedBy = "paper")
    @JsonIgnore
    private RealEstate realestate;
}
