package com.example.akkar2.entities;

import javax.persistence.*;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.util.Date;

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
    @ManyToOne
    private Furniture furniture;

}

