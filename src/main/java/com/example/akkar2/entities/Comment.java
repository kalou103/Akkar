package com.example.akkar2.entities;

import javax.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@ToString
public class Comment {


        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        Long CommentID;

        String text;

        @Temporal(TemporalType.DATE)
        Date date;
        Integer likes;
        @ManyToOne
        private User user;

    @ManyToOne
    private Post post;
        //Les commandes lier a cet furniture



    }




