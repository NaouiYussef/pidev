package com.example.pidev.RestControllers;

import com.example.pidev.DAO.Entities.Categoryp;
import com.example.pidev.DAO.Entities.Product;
import com.example.pidev.Service.Interface.ICategory;
import com.example.pidev.Service.Interface.IProduct;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
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

    @DeleteMapping("/admin/deleteProdbyID/{id}")
    public String delete(@PathVariable Long id)
    {
        iprod.deleteById(id);
        return "Deleted";
    }
}
