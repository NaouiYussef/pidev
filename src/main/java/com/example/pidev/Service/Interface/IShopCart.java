package com.example.pidev.Service.Interface;

import com.example.pidev.DAO.Entities.Commande;
import com.example.pidev.DAO.Entities.LigneDeCommande;
import com.example.pidev.DAO.Entities.ShoppingCart;
import com.example.pidev.DAO.Entities.User;

import java.util.List;
import java.util.Set;

public interface IShopCart {
    ShoppingCart add(User u);
    void supprimerLignePanier(Long id, Long l);
    ShoppingCart ajouterLigne( Long id, LigneDeCommande l) ;

    ShoppingCart edit(Long s );
    List<ShoppingCart> selectAll();
    ShoppingCart SelectById(Long id);
    void deleteById(Long id);
}
