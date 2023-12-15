package org.example.Social.App.Service;

import org.example.Social.App.Model.Comment;
import org.example.Social.App.Model.Post;
import org.example.Social.App.Model.User;
import org.example.Social.App.Repository.ICommentRepo;
import org.example.Social.App.Repository.IPostRepo;
import org.example.Social.App.Repository.IUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {

    @Autowired
    ICommentRepo commentRepo;

    @Autowired
    IPostRepo postRepo;

    @Autowired
    IUserRepo userRepo;

    public String commentOnPost(Comment comment) {
        //Check if post and commenter are valid
        Post post = postRepo.findById(comment.getPost().getPostId()).orElse(null);
        User commenter = userRepo.findById(comment.getCommenter().getUserId()).orElse(null);

        if(post == null || commenter == null)
            return "Data Invalid";
        comment.setCommenter(commenter);
        comment.setPost(post);

        commentRepo.save(comment);

        return "Commented on post";
    }

    public String deleteComment(Integer commentId) {
        commentRepo.deleteById(commentId);
        return "Comment Deleted";
    }
}
