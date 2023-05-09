package com.example.pidev.Service.Interface;

import com.example.pidev.Dto.DAO.Entities.Categoryp;

import java.util.List;

public interface ICategory {

    Categoryp add(Categoryp c);
    Categoryp edit(Categoryp c );
    List<Categoryp> selectAll();
    Categoryp SelectById(Long id);
    void deleteById(Long id);}
