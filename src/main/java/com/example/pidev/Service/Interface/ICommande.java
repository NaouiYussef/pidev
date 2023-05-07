package com.example.pidev.Service.Interface;

import com.example.pidev.DAO.Entities.Commande;

import java.util.List;

public interface ICommande {
    Commande add(Commande s);
    Commande edit(Commande s );
    List<Commande> selectAll();
    Commande SelectById(Long id);
    void deleteById(Long id);
}
