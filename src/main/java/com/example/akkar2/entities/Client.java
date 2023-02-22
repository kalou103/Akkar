package com.example.akkar2.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import lombok.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@DiscriminatorValue("C")
public class Client extends User  implements Serializable {
    @Column(nullable = true)
    private String type ;

    @Column(nullable = true)
    private int visitNumber;

    @Column(nullable = true)
    private int announcementAvailability;
    //announcement

    @OneToMany(mappedBy ="client" )
    @JsonIgnore
    private List<Announcement> announcements;
    // reports

    @OneToMany(cascade = CascadeType.ALL, mappedBy="client")
    @JsonIgnore
    private List<Reclamation> reclamations ;
    //TransportationDemand
    //@JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy="client")
    @JsonIgnore
    private List<TransportationDemand> transportationDemands;
    //contact
   // @JsonIgnore
    @ManyToMany(cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Contract> contracts;
 //   @JsonIgnore
    @OneToMany(mappedBy = "client")
    @JsonIgnore
    private List<Reservation> reservations ;
    @OneToMany(mappedBy = "client")
    @JsonIgnore
    private List<ExpertAnalysis> expertAnalysis ;


}
