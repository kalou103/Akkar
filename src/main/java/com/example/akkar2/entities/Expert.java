package com.example.akkar2.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@DiscriminatorValue("E")

public class Expert extends User  implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idExpert")
    private int id;

    private long cin ;

    private String pictures;

    private  double expertPrice;

    private  String expertLocation;

    private  String description;
    @OneToMany(cascade = CascadeType.ALL, mappedBy="expert")
    private Set<ExpertAnalysis> expertAnalysis;
    @OneToMany(cascade = CascadeType.ALL, mappedBy="expert")
    private Set<ExpertAppointment> expertAppointments;

}
