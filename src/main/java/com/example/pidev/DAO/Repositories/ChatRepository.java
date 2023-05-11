package com.example.pidev.DAO.Repositories;

import com.example.pidev.DAO.Entities.Chat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository

public interface ChatRepository extends JpaRepository<Chat, Long> {
    // List<Chat> findByPartecipant(String user);
    @Query("SELECT i FROM Chat i WHERE i.name =:name")
    Chat findByName(@Param("name")String chat);
}
