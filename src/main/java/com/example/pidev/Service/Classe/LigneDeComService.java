package com.example.pidev.Service.Classe;

import com.example.pidev.DAO.Entities.LigneDeCommande;
import com.example.pidev.DAO.Entities.Product;
import com.example.pidev.DAO.Entities.ShoppingCart;
import com.example.pidev.DAO.Repositories.CommandeRepositories;
import com.example.pidev.DAO.Repositories.LigneDeComRepositories;
import com.example.pidev.DAO.Repositories.ProductRepositories;
import com.example.pidev.DAO.Repositories.ShopCartRepositories;
import com.example.pidev.Service.Interface.ILigneDeCom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LigneDeComService implements ILigneDeCom {
    @Autowired
    LigneDeComRepositories cr;
    @Autowired
    ProductRepositories pr;
    @Autowired
    ShopCartRepositories cc;
    @Override
    public LigneDeCommande add(Long idPanier,Long productId) {
      LigneDeCommande l = new LigneDeCommande();
      ShoppingCart p = cc.findShoppingCartById(idPanier);
      l.setPanier(p);
      Product product =pr.getReferenceById(productId);
      l.setProduct(product);
       l.setQuantity(1L);
       l.setPrixT((long) (l.getQuantity() * l.getProduct().getPrix()));
       return cr.save(l);

    }


//    @Override
//    public LigneDeCommande addToBasket(Long productId, int quantity, LigneDeCommande ligneDeCommande) {
//        Product product = cr.findByProductId(productId).get().getProduct();
//
//        if (product==null) {
//            ligneDeCommande.setProduct(product);
//            System.out.println(ligneDeCommande.getProduct());
//            ligneDeCommande.setQuantity(quantity);
//
//        } else {
//            ligneDeCommande.setQuantity(ligneDeCommande.getQuantity() + quantity);
//
//        }
//        return cr.save(ligneDeCommande);
//    }


    @Override
    public LigneDeCommande edit(LigneDeCommande s) {
        return cr.save(s);
    }

    @Override
    public List<LigneDeCommande> selectAll(Long id) {

        List<LigneDeCommande> ListLigne= cr.findAll();
        List<LigneDeCommande> MaListe= new ArrayList<>();
for (LigneDeCommande l:ListLigne)
{
    if(id==l.getPanier().getId())
    {

        MaListe.add(l);
    }

}
        return MaListe;
    }


    @Override
    public LigneDeCommande SelectById(Long id) {
        return cr.findById(id).get();
    }

    @Override
    public void deleteById(Long id) {
        cr.deleteById(id);
    }
}
