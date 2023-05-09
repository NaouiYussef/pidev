package com.example.pidev.Service.Interface;

import com.example.pidev.Dto.DAO.Entities.Product;

import java.util.List;

public interface IProduct {
    Product add(Product p);
    Product edit(Product p );
    List<Product> selectAll();
    Product SelectById(Long id);
    void deleteById(Long id);

    List<Product> searchProducts(String searchQuery);

    List<Product> sortProductsByPrice();

}
