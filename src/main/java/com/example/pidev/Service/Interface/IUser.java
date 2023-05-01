package com.example.pidev.Service.Interface;

import com.example.pidev.DAO.Entities.User;

import java.util.List;
import java.util.Optional;

public interface IUser {
    void add(User a);
    User edit(User a);
    List<User> selectAll();
    User SelectById(int id);
    void deleteById(int id);
    User getUserByEmail(String email);

}
