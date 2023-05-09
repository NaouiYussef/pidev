package com.example.pidev.Service.Classe;

import com.example.pidev.Dto.DAO.Entities.Product;
import com.example.pidev.Dto.DAO.Entities.ProductDetails;
import com.example.pidev.Dto.DAO.Repositories.AttributeRepositories;
import com.example.pidev.Dto.DAO.Repositories.ProductDetailsRepositories;
import com.example.pidev.Dto.DAO.Repositories.ProductRepositories;
import com.example.pidev.Service.Interface.IProduct;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
@AllArgsConstructor
public class ProductService implements IProduct {
    @Autowired
    ProductRepositories productRepository;
    @Autowired
    ProductDetailsRepositories productDetailsRepositories;
    @Autowired
    AttributeRepositories attributeRepositories;


    @Override
    public Product add(Product p) {
        productDetailsRepositories.saveAll(p.getDetails());
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
    @Override
    public List<Product> searchProducts(String searchQuery) {
        System.out.println(searchQuery);
        List<Product> matchingProducts = new ArrayList<>();
        for (Product product : productRepository.findAll()) {

            if (product.getProductName().toLowerCase().contains(searchQuery.toLowerCase())) {
                System.out.println("search by name");

                matchingProducts.add(product);
            }

            else if (product.getCategoryp().getCategoryName().toLowerCase().contains(searchQuery.toLowerCase())) {
                System.out.println("search by Category");

                matchingProducts.add(product);
            }

            else {
                System.out.println("search by Details");

                for (ProductDetails detail : product.getDetails()) {
                    if (detail.getValue().toLowerCase().contains(searchQuery.toLowerCase())) {
                        matchingProducts.add(product);
                        break;
                    }
                }
            }
        }
        return matchingProducts;
    }
    @Override
    public List<Product> sortProductsByPrice() {
        List<Product> products=productRepository.findAll();
        products.sort(Comparator.comparingDouble(Product::getPrix));
        return products;
    }
}
