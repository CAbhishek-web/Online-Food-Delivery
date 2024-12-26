package com.foodapp.Dao;

import java.util.List;

import com.foodapp.model.User;

public interface UserDAO {
    int insertUser(User user);
    List<User> fetchAllUsers();
    User fetchUserById(String username, String password);
    void deleteUser(int userId);
}

