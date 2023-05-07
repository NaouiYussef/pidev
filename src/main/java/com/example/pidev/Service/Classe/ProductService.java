package com.example.pidev.Service.Classe;

import com.example.pidev.DAO.Entities.Product;
import com.example.pidev.DAO.Repositories.ProductRepositories;
import com.example.pidev.Service.Interface.IProduct;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductService implements IProduct {
    @Autowired
    ProductRepositories productRepository;

    @Override
    public Product add(Product p) {
        return productRepository.save(p);
    }

    @Override
    public Product edit(Product p) {
        return productRepository.save(p);
    }

    @Override
    public List<Product> selectAll() {
        return productRepository.findAll();
    }

    @Override
    public Product SelectById(Long id) {
        if(productRepository.findById(id).isPresent())
            return productRepository.findById(id).get();
        return null;
    }

    @Override
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }
}
