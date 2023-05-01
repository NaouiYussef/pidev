package com.example.pidev.DAO.Repositories;

import com.example.pidev.DAO.Entities.ProductDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductDetailsRepositories extends JpaRepository<ProductDetails, Long> {
}
