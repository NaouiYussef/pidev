package com.example.pidev.RestControllers;

import com.example.pidev.Dto.DAO.Entities.Categoryp;
import com.example.pidev.Service.Interface.ICategory;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/category")
public class CateogrypRestControllers {
    private ICategory icat;

    @PostMapping("/addCat")
    public Categoryp ajouter (@RequestBody Categoryp cat)
    {
        return  icat.add(cat);
    }

    @PutMapping("/updatecat")
    public Categoryp update(@RequestBody Categoryp cat)
    {
        return icat.edit(cat);
    }
    @GetMapping("/showAll")
    public List<Categoryp> findAll()
    {
        return icat.selectAll();
    }
    @GetMapping("/showCatById/{id}")
    public Categoryp AfficherByID(@PathVariable Long id)
    {
        return icat.SelectById(id);
    }

    @DeleteMapping("/deleteCatbyID/{id}")
    public String delete(@PathVariable Long id)
    {
        icat.deleteById(id);
        return "Deleted";
    }
}
