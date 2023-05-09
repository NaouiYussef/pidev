package com.example.pidev.Dto.DAO.Repositories;

import com.example.pidev.Dto.DAO.Entities.ProductDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductDetailsRepositories extends JpaRepository<ProductDetails, Long> {
}
