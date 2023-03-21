package com.example.pidev.RestControllers;

import com.example.pidev.DAO.Entities.User;
import com.example.pidev.Service.Interface.IUser;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor

public class UserRestControllers {
    private IUser iUser;
    @PostMapping("/ajouteruser")
    public void ajouter (@RequestBody User user)
    {
        iUser.add(user);}

}
