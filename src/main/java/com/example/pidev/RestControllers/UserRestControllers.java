package com.example.pidev.RestControllers;

import com.example.pidev.DAO.Entities.User;
import com.example.pidev.Service.Classe.UserService;
import com.example.pidev.Service.Interface.ISecurity;
import com.example.pidev.Service.Interface.IUser;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class UserRestControllers {
    private IUser iUser;
    private final UserService userService;

private ISecurity iSecurity;

    @PostMapping("/user/add")
    public void ajouter (@RequestBody User user)
    {
        iUser.add(user);}


    @PutMapping("/user/update")
    public User update(@RequestBody User user){
        System.out.println("user modif: " + user.getUsername());

        return userService.edit(user);



    }

    @GetMapping("/admin/afficherUserbyID/{id}")
    public User AfficherByID(@PathVariable int id)
    {
        return iUser.SelectById(id);
    }

    @GetMapping("/admin/all")
    public List<User> AfficherUsers()
    {

        return iUser.selectAll();
    }
    @DeleteMapping("/admin/delete/{id}")
    public void delete(@PathVariable int id)
    {
        iUser.deleteById(id);
    }
    @GetMapping("/session")
    public User getUSerCnnetcte()
    {
        return iUser.getUserByEmail( iSecurity.getCurrentUserName());
    }
}
