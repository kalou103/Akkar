package com.example.akkar2.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class ExpertAppointment implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idExpertAppointment")
    private Long id;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date date;
    private boolean appointmentStatus;
    @ManyToOne
    @JsonIgnore
    private User client;
    @ManyToOne
    @JsonIgnore
    private Expert expert;
    @JsonIgnore
    @ManyToOne
    private RealEstate realEstate;

}
