package com.example.pidev.Service.Classe;

import com.example.pidev.DAO.Entities.Commande;
import com.example.pidev.DAO.Repositories.CommandeRepositories;
import com.example.pidev.Service.Interface.ICommande;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class CommandeService implements ICommande {
    @Autowired
    CommandeRepositories cr;
    @Override
    public Commande add(Commande s) {

        return cr.save(s);
    }

    @Override
    public Commande edit(Commande s) {
        return cr.save(s);
    }

    @Override
    public List<Commande> selectAll() {
        return cr.findAll();
    }

    @Override
    public Commande SelectById(Long id) {
        return cr.findById(id).get();
    }

    @Override
    public void deleteById(Long id) {
        cr.deleteById(id);
    }

}
