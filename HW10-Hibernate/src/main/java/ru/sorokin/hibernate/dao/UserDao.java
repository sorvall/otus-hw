package ru.sorokin.hibernate.dao;

import ru.sorokin.core.sessionmanager.SessionManager;
import ru.sorokin.entity.User;

import java.util.Optional;

public interface UserDao {
    Optional<User> findById(long id);
    //List<User> findAll();

    long insert(User user);

    void update(User user);

    long insertOrUpdate(User user);

    SessionManager getSessionManager();
}
