package com.example.pidev.DAO.Repositories;

import com.example.pidev.DAO.Entities.LigneDeCommande;
import com.example.pidev.DAO.Entities.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShopCartRepositories extends JpaRepository<ShoppingCart,Long> {
ShoppingCart findShoppingCartById (long id);
ShoppingCart findShoppingCartByUserIdUser(long id);


}
