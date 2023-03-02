package com.example.akkar2.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

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
    @NotEmpty(message = "Product Name is mandatory")
    String furnitureName;
    @NotNull(message = "Please provide some price")
    Float furniturePrice;
    @Temporal(TemporalType.DATE)
    Date publicationDate;
    Boolean availability;
    String furniturePicture;
    Integer stock;

@JsonIgnore
   @ManyToMany(mappedBy = "furnitures")
    private Set<Command>  commands;

    @JsonIgnore
    @ManyToOne
    private Discount discount;
    @JsonIgnore
    @ManyToOne
    private User user;

}
