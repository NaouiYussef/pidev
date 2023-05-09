package com.example.pidev.RestControllers;

import com.example.pidev.DAO.Entities.Commande;
import com.example.pidev.DAO.Entities.Product;
import com.example.pidev.DAO.Entities.ShoppingCart;
import com.example.pidev.Service.Classe.CommandeService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/Commande")
public class CommandeRestControllers {
    @Autowired
    CommandeService cs;
    @PostMapping("/addCommande")
    public Commande add(@RequestBody Commande s){
        return cs.add(s);
    }
    @GetMapping("/showCommandes")
    public List<Commande> selectAll(){
        return cs.selectAll();
    }
    @PostMapping("/deleteCommande")
    public void deleteById(Long id){
        cs.deleteById(id);
    }
    @GetMapping("/showCommande/{id}")
    public Commande AfficherByID(@PathVariable Long id)
    {
        return cs.SelectById(id);
    }

}
