package com.example.akkar2.services;

import com.example.akkar2.entities.Comment;
import com.example.akkar2.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService  implements  ICommentService {
    @Autowired
    CommentRepository CommentRepo;
    @Override
    public Comment addComment(Comment c) {
        return CommentRepo.save(c) ;
    }

    @Override
    public List<Comment> retrieveAllComments() {
        return CommentRepo.findAll();
    }



    @Override
    public void removeComment(int id) {
        CommentRepo.deleteById(id);
    }

    @Override
    public Comment updateComment(Comment c) {
        return null;
    }}