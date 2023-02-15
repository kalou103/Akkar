package com.example.akkar2.controllers;

import com.example.akkar2.entities.Post;
import com.example.akkar2.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/Post")
public class PostController {

        @Autowired
        PostService postService;
        @PostMapping("/add-Post")
        @ResponseBody
        public Post AddPost(@RequestBody Post p)
        {
            return postService.addPost(p);
        }
        @GetMapping("/retrieveAllPosts")
        @ResponseBody
        public List<Post> retrieveAllPosts() {
            return postService.retrieveAllPosts();
        }
        @DeleteMapping("/delete-Post/{postId}")
        @ResponseBody
        public void removePost(@PathVariable("postId")int id) {
            postService.removePost(id);
        }
        @PutMapping("/update-Post")
        @ResponseBody
        public Post updatePost(@RequestBody Post p) {
            return postService.updatePost(p);
        }
    }





