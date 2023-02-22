package com.example.akkar2.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Papers implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id ;

    private String title;

    private String description;
    @OneToMany(mappedBy = "papers")
    @JsonIgnore
    private List<LoanPapers> loanPapers = new ArrayList<>();

    @OneToMany
     @JsonIgnore
    private List<CertificateOfOwnership> ownership = new ArrayList<>();

    @OneToMany
    @JsonIgnore
    private List<RealEstatesFees> fees = new ArrayList<>();

    @OneToOne(mappedBy = "paper")
    @JsonIgnore
    private RealEstate realestate;
}
