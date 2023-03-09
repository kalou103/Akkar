package com.example.akkar2.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

   /* @ManyToMany(cascade = CascadeType.ALL)

    @JsonIgnore
    private List<Furniture> furnitures ;*/
   @ManyToMany
   @JoinTable(
           name = "command_furniture",
           joinColumns = @JoinColumn(name = "commandId"),
           inverseJoinColumns = @JoinColumn(name = "furniture_id")
   )
   @JsonIgnore
   private List<Furniture> furnitures = new ArrayList<>();

   @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
   @JsonIgnore
   private User user;

}