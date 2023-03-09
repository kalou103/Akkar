package com.example.akkar2.services;

import com.example.akkar2.entities.Comment;
import com.example.akkar2.entities.Post;
import com.example.akkar2.entities.User;
import com.example.akkar2.repository.CommentRepository;
import com.example.akkar2.repository.PostRepository;
import com.example.akkar2.repository.UserRepository;
import com.example.akkar2.services.Exceptions.HasForbiddenWordException;
import com.example.akkar2.services.Exceptions.PostDoesNotExistsException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
@Transactional
public class CommentService  {
    private final UserRepository userRepository;
    private final CommentRepository commentsRepository;
    private final PostRepository postRepository;
    public List<Comment> getCommentsFromPost(Long id){
        Post post = postRepository.findById(id).orElseThrow(() -> new PostDoesNotExistsException(
                "Post does not exists"
        ));
        return commentsRepository.findByPost(post);
    }
    public Comment addComment( Long id,Comment comments, Long uid)  {
        User user = userRepository.findUserById(uid);

        comments.setComment(comments.getComment());
        comments.setVoteCount(0);
        comments.setPost(postRepository.findPostByPostId(id));
        comments.setUser(user);
        Boolean exists = postRepository.selectExistsId(id);
        if(!exists){
            throw new PostDoesNotExistsException(
                    "Post Does not exists!"
            );
        }
        checkForbiddenWords(comments.getComment());

        return commentsRepository.save(comments);

    }

    private void checkForbiddenWords(String s){
        String[] forbidden = {"Shit"};
        List<String> forbiddenWords = Arrays.asList(forbidden);

        for (int i=0;i<forbiddenWords.size();i++){

            if(s.contains(forbiddenWords.get(i))){
                log.warn("{} Commented forbidden word");
                throw new HasForbiddenWordException(
                        "Comment has forbbiden word!"
                );
            }
        }
    }
    public Comment giveALike(Long comId, Long userId) {
        Comment comment = commentsRepository.findCommentByCommentId(comId);
        User user = userRepository.findUserById(userId);
        if (comment.getLikedBy().contains(user)) {
            // User has already liked the post, so remove the like
            comment.getLikedBy().remove(user);
            comment.setVoteCount(comment.getVoteCount() - 1);
        } else {
            // User has not yet liked the post, so add the like
            comment.getLikedBy().add(user);
            comment.setVoteCount(comment.getVoteCount() + 1);
        }

        commentsRepository.save(comment);
        return comment;
    }
    public List<Comment> findCommentsForUser(User user){

        return commentsRepository.findByUser(user);
    }






    public void removeComment(Long id) {
        commentsRepository.deleteById(id);
    }

    public Comment updateComment(Comment c) {
     return    commentsRepository.save(c);
    }


}