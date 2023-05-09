package com.example.pidev.Service.Interface.Interface;


import com.example.pidev.Dto.DAO.Entities.Role;

import java.util.List;

public interface IRole {
    void add(Role a);
    void deleteById(int id);
    List<Role> selectAll();
}
