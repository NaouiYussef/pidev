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
    @PostMapping("/{id}/addShop")
    public ShoppingCart add(@PathVariable int id,@RequestBody ShoppingCart s  ){
        return sc.add(s,id);
    }
    @GetMapping ("/showShop")
    public List<ShoppingCart> selectAll(){
        return sc.selectAll();
    }
    @PutMapping("/editShop")
    public ShoppingCart edit(@RequestBody ShoppingCart s ){
        return sc.edit(s);
    }
    @PostMapping("/deleteShop")
    public void deleteById(@RequestBody Long id){
        sc.deleteById(id);
    }
    @GetMapping("/{id}/select")
    public ShoppingCart SelectById(@PathVariable Long id){ return sc.SelectById(id);}
    @DeleteMapping ("/{id}/deleteLigne/{l}")
    public  void supprimerLignePanier(@PathVariable Long id, @PathVariable long l){sc.supprimerLignePanier(id,l);}
    @PostMapping("/{id}/addLigneShop")
    public ShoppingCart ajouterLigne(@PathVariable  Long id,@RequestBody LigneDeCommande l) { return sc.ajouterLigne(id,l);}

}
