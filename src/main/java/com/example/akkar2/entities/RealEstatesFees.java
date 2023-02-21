package com.example.akkar2.entities;

import lombok.*;

import javax.persistence.*;
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "RealEstatesFees")
public class RealEstatesFees {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String imageName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "paper_id")
    private Papers papers;
}
