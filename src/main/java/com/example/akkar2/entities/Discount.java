package com.example.akkar2.entities;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;

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
public class Discount  implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long discountId;
    Double discountPrice;
    String discountTitle;
    @Temporal(TemporalType.DATE)
    Date startingDate;
    @Temporal(TemporalType.DATE)
    Date endingDate;
    String discountPicture;
    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL ,mappedBy = "discount")
    @JsonIgnore
    private List<Furniture> furnitures = new ArrayList<>();

}

