package com.example.akkar2.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode

public class Reclamation  implements Serializable {



        @Id
        @GeneratedValue(strategy= GenerationType.IDENTITY)
        @Column(name="idReclamation")
        private long idReclamation;
        @Column(nullable = false)
        private String title;
        @Column(nullable = false)
        private String content;
        @Column(nullable = false)
        private String reason;
        @Column(nullable = false)
        private boolean status;

        @ManyToOne
        @JsonIgnore
        private Client client;
        @ManyToOne
        @JsonIgnore
        private Announcement announcement;



}
