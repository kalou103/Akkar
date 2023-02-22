package com.example.akkar2.controllers;

import com.example.akkar2.entities.Comment;
import com.example.akkar2.entities.User;
import com.example.akkar2.repository.UserRepository;
import com.example.akkar2.services.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@AllArgsConstructor
@RequestMapping("/Comment")
public class CommentController {

        CommentService commentsService;
        UserRepository userDao;



        @PostMapping("/add/{id}/{userid}")
        public Comment addComment(@PathVariable Long id, @RequestBody Comment comments, @PathVariable Long  userid){



            return commentsService.addComment(id, comments, userid);

        }


    @GetMapping("/get/findBy/user/{uid}")
    public List<Comment> getCommentsForUser(@PathVariable("uid")Long uid){
        User user = userDao.findUserById(uid);

        return commentsService.findCommentsForUser(user);
    }

    @GetMapping("/get/post/{id}")
    public List<Comment> getCommentsByPost(@PathVariable Long id){

        return commentsService.getCommentsFromPost(id);
    }
    @DeleteMapping("/delete/{Id}")
    @ResponseBody
    public void removeComment(@PathVariable("Id")Long id) {
        commentsService.removeComment(id);
    }
}

