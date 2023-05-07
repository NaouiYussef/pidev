package com.example.pidev.DAO.Repositories;

import com.example.pidev.DAO.Entities.Commande;
import com.example.pidev.DAO.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.jws.soap.SOAPBinding;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface CommandeRepositories extends JpaRepository<Commande,Long> {


}
