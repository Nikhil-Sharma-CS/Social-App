package org.example.Social.App.Repository;

import org.example.Social.App.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserRepo extends JpaRepository<User, Integer> {

    User getByuserName(String userName);

    User findByuserEmail(String email);

    Optional<User> getByuserEmail(String userEmail);
}
