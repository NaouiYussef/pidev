package com.example.pidev.RestControllers;

import com.example.pidev.DAO.Entities.User;
import com.example.pidev.Service.Interface.IUser;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor

public class UserRestControllers {
    private IUser iUser;
    @PostMapping("/ajouteruser")
    public void ajouter (@RequestBody User user)
    {
        iUser.add(user);}
    @PutMapping("/user/updateuser")
    public User update(@RequestBody User user)
    {return iUser.edit(user);
    }
    @GetMapping("/afficherUserbyID/{id}")
    public User AfficherByID(@PathVariable int id)
    {
        return iUser.SelectById(id);
    }
    @DeleteMapping("/admin/deleteUserbyID/{id}")
    public void delete(@PathVariable int id)
    {
        iUser.deleteById(id);
    }

}
