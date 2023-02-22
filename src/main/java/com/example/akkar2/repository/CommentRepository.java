package com.example.akkar2.repository;

import com.example.akkar2.entities.Comment;
import com.example.akkar2.entities.Post;
import com.example.akkar2.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository  extends JpaRepository<Comment, Long> {



    List<Comment> findByUser(User user);
    List<Comment> findByPost(Post post);

}
