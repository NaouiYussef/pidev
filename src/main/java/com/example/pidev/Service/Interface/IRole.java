package com.example.pidev.Service.Interface;

import com.example.pidev.DAO.Entities.Role;
import com.example.pidev.DAO.Entities.User;

public interface IRole {
    void add(Role a);
    void deleteById(int id);
}
