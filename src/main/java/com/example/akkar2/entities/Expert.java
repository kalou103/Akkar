package com.example.akkar2.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.io.Serializable;
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

    private long cin ;

    private String pictures;

    private  double expertPrice;

    private  String expertLocation;

    private  String description;
    private boolean status=Boolean.FALSE;
    @OneToMany(cascade = CascadeType.ALL, mappedBy="expert")
    @JsonIgnore
    private Set<ExpertAnalysis> expertAnalysis;
    @OneToMany(cascade = CascadeType.ALL, mappedBy="expert")
    @JsonIgnore
    private Set<ExpertAppointment> expertAppointments;

}
