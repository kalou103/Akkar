package com.example.akkar2.entities;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import static javax.persistence.FetchType.LAZY;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@ToString
@Data
public class Post implements Serializable{

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        Long postId;

        String postTitle;
        String postUrl;
        Integer voteCount;
    @Lob
    private String postDescription;
    @Enumerated(EnumType.STRING)
    private PostTopic category;
    @CreationTimestamp
    private Date dateCreated;
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "uid", referencedColumnName = "id")
@JsonIgnore
    private User user;


        //Les commandes lier a cet furniture


        //l'utilisateur owner du furniture
    @JsonIgnore
        @OneToMany
                (cascade = CascadeType.ALL, mappedBy="post")
        private List<Comment> comments;

}


