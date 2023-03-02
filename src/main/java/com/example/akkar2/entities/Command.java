package com.example.akkar2.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@ToString
public class Command  implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long commandId;

    Integer quantity;
    @Temporal(TemporalType.DATE)
    Date commandDate;
    @JsonIgnore
    Double totalPrice;
    Boolean payementStatus;

    @ManyToMany
    @JsonIgnore
    private Set<Furniture> furnitures ;

   @ManyToOne
   @JsonIgnore
   private User user;

}