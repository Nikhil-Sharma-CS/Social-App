package org.example.Social.App.Repository;

import org.example.Social.App.Model.Comment;
import org.example.Social.App.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICommentRepo extends JpaRepository<Comment, Integer> {
}
