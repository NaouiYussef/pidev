package com.example.pidev.RestControllers;

import com.example.pidev.Dto.DAO.Entities.Product;
import com.example.pidev.Service.Interface.IProduct;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/Product")
public class ProductRestControllers {
    private IProduct iprod;

    @PostMapping("/addprod")
    public Product ajouter (@RequestBody Product prod)
    {
        return  iprod.add(prod);
    }

    @PutMapping("/updateprod")
    public Product update(@RequestBody Product prod)
    {
        return iprod.edit(prod);
    }
    @GetMapping("/showAll")
    public List<Product> findAll()
    {
        return iprod.selectAll();
    }
    @GetMapping("/showProdById/{id}")
    public Product AfficherByID(@PathVariable Long id)
    {
        return iprod.SelectById(id);
    }

    @DeleteMapping("/deleteProdbyID/{id}")
    public String delete(@PathVariable Long id)
    {
        iprod.deleteById(id);
        return "Deleted";
    }

    @GetMapping("/searchProd/{string}")
    public List<Product> searchProducts(@PathVariable String string)
    {
        return iprod.searchProducts(string);
    }

    @GetMapping("/sortByPrice")
    public List<Product> sortProductsByPrice() {
        List<Product> sortedProducts = iprod.sortProductsByPrice();
        return sortedProducts;
    }
}
