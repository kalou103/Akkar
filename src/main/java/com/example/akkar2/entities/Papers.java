package com.example.akkar2.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
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
    @Column(name = "idpapers")

    private String title;

    private String description;
@JsonIgnore
    @OneToMany(mappedBy = "papers", cascade = CascadeType.ALL)
    private List<LoanPapers> LoanPics;

    @JsonIgnore
    @OneToMany(mappedBy = "papers", cascade = CascadeType.ALL)
    private List<AuthentificationCertificate> AuthPics;

    @JsonIgnore
    @OneToMany(mappedBy = "papers", cascade = CascadeType.ALL)
    private List<CertificateOfOwnership> OwnPics;

    @JsonIgnore
    @OneToMany(mappedBy = "papers", cascade = CascadeType.ALL)
    private List<RealEstatesFees> FeesPics;


    @OneToOne(mappedBy = "paper")
    @JsonIgnore
    private RealEstate realestate;
}
