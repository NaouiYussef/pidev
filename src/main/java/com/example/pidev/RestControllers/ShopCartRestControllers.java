package com.example.pidev.RestControllers;

import com.example.pidev.DAO.Entities.Commande;
import com.example.pidev.DAO.Entities.LigneDeCommande;
import com.example.pidev.DAO.Entities.ShoppingCart;
import com.example.pidev.DAO.Entities.User;
import com.example.pidev.Service.Classe.ShopCartService;
import com.example.pidev.Service.Interface.IShopCart;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;
import java.util.Set;

@RestController
@AllArgsConstructor
@RequestMapping("/ShoppingCart")
public class ShopCartRestControllers {
    @Autowired
    ShopCartService sc;
    @PostMapping("/addCart")
    public ShoppingCart add(@RequestBody User s  ){

        System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        return sc.add(s);
    }
    @GetMapping ("/showShop")
    public List<ShoppingCart> selectAll(){
        return sc.selectAll();
    }
    @PutMapping("/editShop/{id}")
    public ShoppingCart edit(@PathVariable Long id ){
        return sc.edit(id);
    }
    @PostMapping("/deleteShop")
    public void deleteById(@RequestBody Long id){
        sc.deleteById(id);
    }
    @GetMapping("/{id}/select")
    public ShoppingCart SelectById(@PathVariable Long id){ return sc.SelectById(id);}
    @DeleteMapping ("/deleteLigne/{l}/{id}")
    public  void supprimerLignePanier(@PathVariable Long id, @PathVariable Long l){
        System.out.println("ebebebehzybhbedibdia");
        sc.supprimerLignePanier(id,l);}
    @PostMapping("/{id}/addLigneShop")
    public ShoppingCart ajouterLigne(@PathVariable  Long id,@RequestBody LigneDeCommande l) { return sc.ajouterLigne(id,l);}

}
