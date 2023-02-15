package com.example.akkar2.controllers;

import com.example.akkar2.entities.Comment;
import com.example.akkar2.services.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/Comment")
public class CommentController {




        @Autowired
        ICommentService commentService;
        @PostMapping("/add-Comment")
        @ResponseBody
        public Comment AddComment(@RequestBody Comment c)
        {
            return commentService.addComment(c);
        }
        @GetMapping("/retrieveAllComments")
        @ResponseBody
        public List<Comment> retrieveAllComments() {
            return commentService.retrieveAllComments();
        }
        @DeleteMapping("/delete-Comment/{CommentId}")
        @ResponseBody
        public void removeComment(@PathVariable("CommentId")int id) {
            commentService.removeComment(id);
        }

        @PutMapping("/update-Comment")
        @ResponseBody
        public Comment updateComment(@RequestBody Comment c) {
            return commentService.updateComment(c);
        }
}
