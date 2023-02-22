package com.example.akkar2.controllers;

import com.example.akkar2.entities.Post;
import com.example.akkar2.entities.User;
import com.example.akkar2.repository.UserRepository;
import com.example.akkar2.services.PostService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("Post")
public class PostController {
      PostService postService;
      UserRepository userDao;

    @PostMapping("/add/{userid}")
    public void addPost(@RequestBody Post post,@PathVariable("userid")  Long userid){

        postService.createPost(post, userid);
    }



    @GetMapping("/findBy/user/{userid}")
    public List<Post> findByPostsByUserid(@PathVariable("userid")Long userid){

        User user = userDao.findUserById(userid);

        return postService.findPostByUser(user);
    }

    @GetMapping("/find/{name}")
    public Iterable<Post> findByName(@PathVariable("name") String name)
    {
        return postService.SearchPostsByName(name);
    }

    @GetMapping("/trending")
    public Iterable<Post> findTrendingPosts(){

        return postService.findMostLikedPosts();
    }


    @GetMapping("/user/{username}")
    public List<Post> searchPostByUser(@PathVariable("username")Long username){
        return postService.getPostsByUser(userDao.findUserById(username));
    }


    @GetMapping("/all")
    public Iterable<Post> showAllPostsDesc(){

        return postService.showAllPostsDesc();
    }

    @GetMapping("/get/category/{category}")
    public Iterable<Post> showPostsByCategories(@PathVariable("category") String category){

        return postService.showPostsByCategory(category);
    }

    @GetMapping("/get/post/{id}")
    public Optional<Post> getPostById(@PathVariable("id") Long id){


        return postService.selectPost(id);
    }


    @DeleteMapping("/delete/{postId}/{userid}")
    public void deletePostById(@PathVariable("postId")Long postId,@PathVariable("userid")Long userid){



        postService.deletePost(postId, userid);
    }

    @GetMapping("/like/{id}")
    public Post likePostById(@PathVariable("id") Long id){


        return postService.giveALike(id);
    }
    @GetMapping("/dislike/{id}")
    public Post dislikePostById(@PathVariable("id") Long id){


        return postService.giveADisLike(id);
    }

    }





