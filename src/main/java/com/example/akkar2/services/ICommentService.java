package com.example.akkar2.services;

import com.example.akkar2.entities.Comment;

import java.util.List;

public interface ICommentService {
    Comment addComment(Comment c);
    List<Comment> retrieveAllComments();
    void removeComment(int id);
    Comment updateComment(Comment c);
}
