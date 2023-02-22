package com.example.akkar2.entities;

import javax.persistence.*;

import lombok.*;

import java.io.Serializable;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@DiscriminatorValue("A")
public class Admin extends User  implements Serializable {
    //ADMIN ROLE DEFINES A SUPER ADMIN AND ASSISTANT
    @Column(nullable = true)
    private String adminRole ;
}
