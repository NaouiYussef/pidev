package com.example.pidev.Service.Classe;

import com.example.pidev.DAO.Entities.Role;
import com.example.pidev.DAO.Repositories.RoleRepositories;
import com.example.pidev.Service.Interface.IRole;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RoleService implements IRole {
    private RoleRepositories roleRepositories;
    @Override
    public void add(Role a) {
        roleRepositories.save(a);
    }

    @Override
    public void deleteById(int id) {
        roleRepositories.deleteById(id);

    }
}
