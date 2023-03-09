package com.example.akkar2.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

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
public class ExpertAnalysis implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idExpertAnalysis")
    private int id;
    private String pictures;
    private double priceprediction;
    @DateTimeFormat(pattern="dd/MM/yyyy")
    private Date insepctiondate;
    private String description;

    @ManyToOne
    @JsonIgnore
    private User client;
    @ManyToOne
    @JsonIgnore
    private Expert expert;

    @ManyToOne
    @JsonIgnore
    private RealEstate realEstate;


}
