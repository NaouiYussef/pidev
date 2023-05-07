package com.example.pidev.DAO.Repositories;

import com.example.pidev.DAO.Entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepositories extends JpaRepository<Product, Long> {
}
