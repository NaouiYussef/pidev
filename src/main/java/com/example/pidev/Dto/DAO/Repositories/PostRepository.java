package com.example.pidev.Dto.DAO.Repositories;

import com.example.pidev.Dto.DAO.Entities.Post;
import com.example.pidev.Dto.DAO.Entities.Subreddit;
import com.example.pidev.Dto.DAO.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.List;

@Component
public interface PostRepository extends JpaRepository<Post,Long> {
    //List<Post> findAllBySubreddit(Subreddit subreddit);
    List<Post> findBycreatedDateBefore(Instant threshold);
    List<Post> findAllBySubreddit(Subreddit subreddit);
    List<Post> findByUser(User user);
}
