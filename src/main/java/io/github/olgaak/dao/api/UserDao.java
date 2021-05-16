package io.github.olgaak.dao.api;

import io.github.olgaak.entity.Ticket;
import io.github.olgaak.entity.User;

public interface UserDao {
    public User findByEmail(String email);

    void createNewUser(User user);
}
