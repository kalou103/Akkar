package com.example.akkar2.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
        import lombok.*;

        import javax.persistence.*;
        import java.io.Serializable;
        import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Feedback implements Serializable{

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int feedbackid ;
    private String feedbackbody ;
    private Date dateFeedback ;

    @OneToOne
    @JsonIgnore
    private User user;
}