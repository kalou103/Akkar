package com.example.akkar2.entities;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "LoanPapers")
public class LoanPapers {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        @Lob
        private byte[] images;
        @ManyToOne
        private Papers papers;

}


