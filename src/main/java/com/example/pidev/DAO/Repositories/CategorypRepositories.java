package com.example.pidev.DAO.Repositories;

import com.example.pidev.DAO.Entities.Categoryp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategorypRepositories extends JpaRepository<Categoryp,Long> {
}
