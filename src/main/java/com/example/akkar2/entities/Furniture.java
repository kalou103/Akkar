package com.example.akkar2.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@ToString
public class Furniture implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long furnitureId;
    @Enumerated(EnumType.STRING)
    FurnitureCategory furnitureCategory;
    String furnitureName;
    Float furniturePrice;
    @Temporal(TemporalType.DATE)
    Date publicationDate;
    Boolean availability;
    String furniturePicture;
    Integer stock;


    //Les commandes lier a cet furniture
    @JsonIgnore
    @OneToOne(mappedBy = "furniture")
    private Command command;
    //l'utilisateur owner du furniture
    @OneToMany
            (cascade = CascadeType.ALL, mappedBy="furniture")
    private List<Discount> discounts;
    @ManyToOne
    private User user;

}
