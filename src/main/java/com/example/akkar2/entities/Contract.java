package com.example.akkar2.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Contract implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private boolean contractStatus;
    @Lob
    @JsonIgnore
    private byte[] firstSignature;
    @Lob
    @JsonIgnore
    private byte[] secondSignature;
    @Temporal(TemporalType.DATE)
    private Date dateofSignature;
    @Enumerated(EnumType.STRING)
    private ContractType typeContract;
    private String description;
    @ManyToMany(mappedBy="contracts", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Client> client;
    @ManyToOne
    @JsonIgnore
    private RealEstate realEstate;
}
