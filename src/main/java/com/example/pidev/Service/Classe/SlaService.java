package com.example.pidev.Service.Classe;

import com.example.pidev.DAO.Entities.Sla;
import com.example.pidev.DAO.Entities.User;
import com.example.pidev.DAO.Repositories.SlaRepository;
import com.example.pidev.DAO.Repositories.UserRepositories;
import com.example.pidev.Dto.SlaDto;
import com.example.pidev.Service.Interface.ISla;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class SlaService implements ISla {
    private SlaRepository slaRepository;
    private UserRepositories userRepositories;
    private Security security;

    public void save(SlaDto slaDto, MultipartFile file, Integer userId, String providerMail) throws IOException {
        Date date = new Date();
        User p = userRepositories.findByMail(providerMail);
        if (p == null) {
            throw new IllegalArgumentException("Provider not found");
        }
        User u = userRepositories.findById(userId).get();
        Sla sla = new Sla();
        sla.setDate(date);
        sla.setProvider(p);
        sla.setUser(u);
        sla.setData(file.getBytes());
        slaRepository.save(sla);
    }

//    public List<User> searchProviders(String name) {
//        return userRepositories.findUsersByUsername(name);
//    }


    @Override
    public Sla edit(Sla sla) {
        // get the existing SLA from the database
        Sla existingSla = slaRepository.findById(sla.getId()).orElseThrow(() -> new IllegalArgumentException("SLA not found"));

        // set the fields of the existing SLA with the updated fields
        existingSla.setDate(sla.getDate());
        existingSla.setProvider(sla.getProvider());
        existingSla.setUser(sla.getUser());
        existingSla.setData(sla.getData());

        // save the updated SLA to the database
        return slaRepository.save(existingSla);
    }

    @Override
    public void deleteById(int id) {
        slaRepository.deleteById(id);
    }

    @Override
    public List<Sla> selectAll() {
        return slaRepository.findAll();
    }

}
