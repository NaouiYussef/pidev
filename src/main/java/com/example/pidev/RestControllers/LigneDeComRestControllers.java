package com.example.pidev.RestControllers;

import com.example.pidev.DAO.Entities.LigneDeCommande;
import com.example.pidev.DAO.Entities.ShoppingCart;
import com.example.pidev.Service.Classe.LigneDeComService;
import com.example.pidev.Service.Classe.ShopCartService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/LigneDeCommande")
public class LigneDeComRestControllers {
    @Autowired
    LigneDeComService sc;
    @PostMapping("/addLigneDeCommande")
    // public LigneDeCommande addToBasket(@RequestParam Long productId,@RequestParam int quantity,@RequestBody(required = false)  LigneDeCommande ligneDeCommande){
    public LigneDeCommande add(@RequestBody LigneDeCommande s){
        return sc.add(s);
    }

    @GetMapping("/showLigneDeCommande/{id}")
    public List<LigneDeCommande> selectAll(@PathVariable Long id){
        return sc.selectAll(id);
    }
    @PostMapping("/deleteLigneDeCommande")
    public void deleteById(Long id){
        sc.deleteById(id);
    }
}
