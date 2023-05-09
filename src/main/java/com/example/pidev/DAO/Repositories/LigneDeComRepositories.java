package com.example.pidev.DAO.Repositories;

import com.example.pidev.DAO.Entities.LigneDeCommande;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository

public interface LigneDeComRepositories extends JpaRepository<LigneDeCommande,Long> {


    Optional<LigneDeCommande> findByProductId(Long id);
    Optional<LigneDeCommande> findByPanierId(Long id);


    // Optional<LigneDeCommande> findByProductProductName(String name);
}
