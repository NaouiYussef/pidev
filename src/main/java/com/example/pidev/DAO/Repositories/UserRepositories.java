package com.example.pidev.DAO.Repositories;

import com.example.pidev.DAO.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepositories extends JpaRepository<User,Integer> {
    @Override
    User getById(Integer integer);
}
