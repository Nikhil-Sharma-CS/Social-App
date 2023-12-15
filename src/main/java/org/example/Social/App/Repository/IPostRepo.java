package org.example.Social.App.Repository;

import org.example.Social.App.Model.Post;
import org.example.Social.App.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IPostRepo extends JpaRepository<Post, Integer> {
    List<Post> getAllByuser(User user);

    List<Post> getByUserAndIsPrivate(User user, boolean b);
}
