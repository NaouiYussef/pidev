package com.example.pidev.Service.Interface.Interface;


import com.example.pidev.Dto.DAO.Entities.User;

import java.util.List;

public interface IUser {
    void add(User a);
    User edit(User a);
    List<User> selectAll();
    User SelectById(int id);
    void deleteById(int id);
    User getUserByEmail(String email);
}
