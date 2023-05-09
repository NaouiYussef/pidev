package com.example.pidev.DAO.Repositories;

import com.example.pidev.DAO.Entities.Role;
import com.example.pidev.DAO.Entities.Subreddit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface SubredditRepository extends JpaRepository<Subreddit,Long> {
    Optional<Subreddit> findByName(String subredditName);
}

