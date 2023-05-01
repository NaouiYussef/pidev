package com.example.pidev.DAO.Repositories;

import com.example.pidev.DAO.Entities.Post;
import com.example.pidev.DAO.Entities.Role;
import com.example.pidev.DAO.Entities.Subreddit;
import com.example.pidev.DAO.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;

@Component
public interface PostRepository extends JpaRepository<Post,Long> {
    //List<Post> findAllBySubreddit(Subreddit subreddit);
    List<Post> findBycreatedDateBefore(Instant threshold);
    List<Post> findAllBySubreddit(Subreddit subreddit);
    List<Post> findByUser(User user);
}
