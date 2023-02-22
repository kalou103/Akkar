package com.example.akkar2.entities;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "CertificateOfOwnership")
public class CertificateOfOwnership {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private String imageName;

        @ManyToOne
        private Papers papers;

}
