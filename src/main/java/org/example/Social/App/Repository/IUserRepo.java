package org.example.Social.App.Repository;

import org.example.Social.App.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepo extends JpaRepository<User, Integer> {
    User getByuserEmail(String userEmail);

    User getByuserName(String userName);

    User findByuserEmail(String email);
}
