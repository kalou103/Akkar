package com.example.akkar2.entities;

import javax.persistence.*;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@ToString
public class Post implements Serializable{

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        Long PostID;
        @Enumerated(EnumType.STRING)
        PostTopic postTopic;
        String title;
        String text;

        @Temporal(TemporalType.DATE)
        Date postDate;
        Integer likes;
    @ManyToOne
    private User user;


        //Les commandes lier a cet furniture


        //l'utilisateur owner du furniture
        @OneToMany
                (cascade = CascadeType.ALL, mappedBy="post")
        private List<Comment> comments;

}


