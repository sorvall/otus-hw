package ru.sorokin.service;

import ru.sorokin.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    long saveUser(User user);

    Optional<User> getUser(long id);
    List<User> getAll();
}
