package com.example.akkar2.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.*;
import javax.persistence.Id;
import java.io.Serializable;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@DiscriminatorValue("R")
public class Rating implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Min(value = 1, message = "Rating should be between 1 and 5")
    @Max(value = 5, message = "Rating should be between 1 and 5")
    private int stars;

    private String comment;
    public Rating(int stars,String comment)
    {
        this.stars=stars;
        this.comment=comment;
    }
    @ManyToOne
    @JsonIgnore
    private Expert expert;


}
