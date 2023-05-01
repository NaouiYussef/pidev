package com.example.pidev.DAO.Repositories;

import com.example.pidev.DAO.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepositories extends JpaRepository<User,Integer> {
    @Override
    User getById(Integer integer);
    User findByMail(String s);

    Optional<User> findByUsername(String username);
}
