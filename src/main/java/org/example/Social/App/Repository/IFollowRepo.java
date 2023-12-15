package org.example.Social.App.Repository;

import org.example.Social.App.Model.Follow;
import org.example.Social.App.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IFollowRepo extends JpaRepository<Follow, Integer> {

    Follow findByCurrentUserAndCurrentUserFollower(User currentUser, User currentUserFollower);
}
