package com.example.pidev.Dto.DAO.Repositories;

import com.example.pidev.Dto.DAO.Entities.Sla;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
@Transactional(readOnly = true)
public interface SlaRepository extends JpaRepository<Sla,Integer> {
    @Override
    Sla getById(Integer integer);

}
