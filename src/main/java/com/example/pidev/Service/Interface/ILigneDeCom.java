package com.example.pidev.Service.Interface;

import com.example.pidev.DAO.Entities.LigneDeCommande;
import com.example.pidev.DAO.Entities.ShoppingCart;

import java.util.List;

public interface ILigneDeCom {
    LigneDeCommande add(Long idPanier,Long productId);
    LigneDeCommande edit(LigneDeCommande s );
    List<LigneDeCommande> selectAll(Long id);
    LigneDeCommande SelectById(Long id);
    void deleteById(Long id);
    // LigneDeCommande addToBasket(Long productId, int quantity, LigneDeCommande ligneDeCommande);
}
