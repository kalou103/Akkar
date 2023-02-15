package com.example.akkar2.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Contract implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idcontract")
    private int id;

    private boolean contractStatus;
    @Lob
    private byte[] firstSignature;
    @Lob
    private byte[] secondSignature;
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
