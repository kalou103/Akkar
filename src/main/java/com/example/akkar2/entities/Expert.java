package com.example.akkar2.entities;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
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
    public boolean staus;

    private  String description;
    @OneToMany(cascade = CascadeType.ALL, mappedBy="expert")
    @JsonIgnore
    private Set<ExpertAnalysis> expertAnalysis;
    @OneToMany(cascade = CascadeType.ALL, mappedBy="expert")
    @JsonIgnore
    private Set<ExpertAppointment> expertAppointments;
    @OneToMany(mappedBy = "expert", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Rating> ratings = new ArrayList<>();

    public void addRating(Rating rating)
    {
        ratings.add(rating);
        rating.setExpert(this);
    }


}
