package com.example.pidev.DAO.Repositories;

import com.example.pidev.DAO.Entities.Role;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepositories extends JpaRepository<Role,Integer> {
}
