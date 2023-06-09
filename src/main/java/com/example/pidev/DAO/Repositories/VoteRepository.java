package com.example.pidev.DAO.Repositories;

import com.example.pidev.DAO.Entities.Post;
import com.example.pidev.DAO.Entities.User;
import com.example.pidev.DAO.Entities.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface VoteRepository extends JpaRepository<Vote,Long> {
   Optional<Vote> findTopByPostAndUserOrderByVoteIdDesc(Post post, User currentUser);
  // Optional<Vote> findTopByPostOrderByVoteIdDesc(Post post);
}
