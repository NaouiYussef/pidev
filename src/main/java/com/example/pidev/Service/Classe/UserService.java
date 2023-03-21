package com.example.pidev.Service.Classe;

import com.example.pidev.DAO.Entities.User;
import com.example.pidev.DAO.Repositories.UserRepositories;
import com.example.pidev.Service.Interface.IUser;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import lombok.AllArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Service
@AllArgsConstructor
public class UserService implements IUser {
    private UserRepositories userRepositories;
    @Override
    public void add(User a) {
        userRepositories.save(a);
    }

    @Override
    public User edit(User a) {
        return userRepositories.save(a);
    }

    @Override
    public List<User> selectAll() {
        return userRepositories.findAll();
    }

    @Override
    public User SelectById(int id) {
        if(userRepositories.findById(id).isPresent())
        return userRepositories.findById(id).get();
        return null;
    }

    @Override
    public void deleteById(int id) {
         userRepositories.deleteById(id);

    }
}
