package com.example.akkar2.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
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
public class Comment {


        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        Long commentId;
        @NotNull
        String comment;
        Integer voteCount;
    @CreationTimestamp
     Date dateCommented;

    @JsonIgnore
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "uid", referencedColumnName = "id")

    User user;

    @JsonIgnore
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "postId", referencedColumnName = "postId")

    Post post;

    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "comment_likes",
            joinColumns = @JoinColumn(name = "comment_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    List<User> likedBy;

    }




