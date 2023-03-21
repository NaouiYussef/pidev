package com.example.pidev.RestControllers;

import com.example.pidev.DAO.Entities.Role;
import com.example.pidev.DAO.Entities.User;
import com.example.pidev.Service.Interface.IRole;
import com.example.pidev.Service.Interface.IUser;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor

public class RoleRestControllers {
    private IRole iRole;
    @PostMapping("/ajouterrole")
    public void ajouter (@RequestBody Role role)
    {
        iRole.add(role);}
}
