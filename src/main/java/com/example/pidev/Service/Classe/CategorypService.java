package com.example.pidev.Service.Classe;

import com.example.pidev.DAO.Entities.Categoryp;
import com.example.pidev.DAO.Repositories.CategorypRepositories;
import com.example.pidev.Service.Interface.ICategory;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class CategorypService implements ICategory {

    @Autowired
    CategorypRepositories categorypRepository;

    @Override
    public Categoryp add(Categoryp c) {
        return categorypRepository.save(c);

    }

    @Override
    public Categoryp edit(Categoryp c) {
        return categorypRepository.save(c);
    }

    @Override
    public List<Categoryp> selectAll() {
        return categorypRepository.findAll();
    }

    @Override
    public Categoryp SelectById(Long id) {

        if(categorypRepository.findById(id).isPresent())
            return categorypRepository.findById(id).get();
        return null;
    }

    @Override
    public void deleteById(Long id) {
        categorypRepository.deleteById(id);

    }
}
