package com.example.akkar2.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.util.ArrayList;
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
    Integer salesCount; // Champ pour stocker le nombre de ventes
    @Transient
    private MultipartFile furnitureImage;

    /*@JsonIgnore
    @ManyToMany(mappedBy = "furnitures")
    private List<Command>  commands;*/
    @ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL ,mappedBy = "furnitures")
    @JsonIgnore
    private List<Command> commands = new ArrayList<>();

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL )
    private Discount discount;
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL )
    private User user;


}
