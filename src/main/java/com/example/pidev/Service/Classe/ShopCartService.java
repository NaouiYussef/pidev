package com.example.pidev.Service.Classe;

import com.example.pidev.DAO.Entities.*;
import com.example.pidev.DAO.Repositories.*;
import com.example.pidev.Service.Interface.ICommande;
import com.example.pidev.Service.Interface.IShopCart;
import com.example.pidev.Service.Interface.IUser;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;
import java.util.*;

@Service
@AllArgsConstructor
public class ShopCartService implements IShopCart {
    @Autowired
    UserRepositories userRepositories;
    @Autowired
    ShopCartRepositories sc;
    @Autowired
    CommandeRepositories cc;
    @Autowired
    LigneDeComRepositories cr;
    @Autowired
    ProductRepositories pr;

    @Override
    public ShoppingCart add(ShoppingCart s  , int u) {

        User user= userRepositories.getById(u);
System.out.println(user.getUsername());
//        if (s.getEtat().equals(Eetat.Valider)){
//            ShoppingCart shoppingCart = new ShoppingCart();
            s.setEtat(Eetat.non_Valider);
            s.setUser(user);
        System.out.println("aaaaaaaaaaaaaaaaa  "+ s.getUser().getUsername());
            return sc.save(s);

    }
    public ShoppingCart ajouterLigne( Long id, LigneDeCommande l) {
        ShoppingCart panier = sc.findShoppingCartById(id);
        if (panier.getEtat().equals(Eetat.non_Valider)){
            Optional<LigneDeCommande> existingLc = cr.findByProductId(l.getProduct().getId());
            if (existingLc.isPresent()) {
                LigneDeCommande lc = existingLc.get();
                lc.setQuantity(l.getQuantity() + lc.getQuantity());
                lc.setPrixT((long) (lc.getQuantity() * lc.getProduct().getPrix()));
                cr.save(lc);
            } else {

                panier.getLigneDeCommandes().add(l);
                l.setPanier(sc.findShoppingCartById(id));
                l.setPrixT((long) (l.getQuantity() * l.getProduct().getPrix()));

                cr.save(l);
                sc.save(panier);
            }

            List<LigneDeCommande> ls=panier.getLigneDeCommandes();
            for (LigneDeCommande u:ls ) {
                panier.setTotal(panier.getTotal()+u.getPrixT());

            }


        }
        else {
            ShoppingCart shoppingCart = new ShoppingCart();
            shoppingCart.setEtat(Eetat.non_Valider);
            shoppingCart.setUser(panier.getUser());
            shoppingCart.getLigneDeCommandes().add(l);
            l.setPanier(sc.findShoppingCartById(id));
            l.setPrixT((long) (l.getQuantity() * l.getProduct().getPrix()));

            cr.save(l);
            sc.save(shoppingCart);
            return sc.save(shoppingCart);

        }
        return sc.save(panier);
    }

    public void supprimerLignePanier(Long id , Long l) {
        ShoppingCart panier = sc.findShoppingCartById(id);
        List <LigneDeCommande> ls=panier.getLigneDeCommandes();
        for (LigneDeCommande u:ls ) {
            if(u.getId()==l){}

        }
        panier.getLigneDeCommandes().remove(l);
        sc.save(panier);
    }




    @Override
    public ShoppingCart edit(ShoppingCart s) {

        ShoppingCart panier = sc.findShoppingCartById(s.getId());
        panier.setEtat(s.getEtat());
        List<LigneDeCommande> ls = panier.getLigneDeCommandes();
        if(panier.getEtat().equals(Eetat.Valider)) {
            Commande c = new Commande();
            c.setDate_commande(LocalDateTime.now());

            c.setShoppingCart(panier);
            c.setConsumer(panier.getUser());
            c.setPrix_total(panier.getTotal());
            cc.save(c);
            for (LigneDeCommande l:ls
            ) {
                ls.remove(l);
                sc.save(s);
            }
            s.setEtat(Eetat.non_Valider);
        } else {
            s.setLigneDeCommandes(ls);
        }
        sc.save(s);
        return s;
    }

    @Override
    public List<ShoppingCart> selectAll() {



        return sc.findAll();


    }

    @Override
    public ShoppingCart SelectById(Long id) {

        ShoppingCart s =sc.findById(id).get();

        System.out.println(s.getLigneDeCommandes());
        return s;




    }

    @Override
    public void deleteById(Long id) {
        sc.deleteById(id);
    }
}
