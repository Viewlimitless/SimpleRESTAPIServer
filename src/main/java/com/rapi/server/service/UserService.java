package com.rapi.server.service;

import com.rapi.server.entity.User;

import java.util.List;

public interface UserService {
    List<User> getAll();

    User getById(long id);

    User save(User user);

    void remove(long id);
}
