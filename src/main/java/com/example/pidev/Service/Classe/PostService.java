package com.example.pidev.Service.Classe;


import com.example.pidev.DAO.Entities.Post;
import com.example.pidev.DAO.Entities.Subreddit;
import com.example.pidev.DAO.Entities.User;
import com.example.pidev.DAO.Repositories.PostRepository;
import com.example.pidev.DAO.Repositories.SubredditRepository;
import com.example.pidev.DAO.Repositories.UserRepositories;
import com.example.pidev.Dto.PostRequest;
import com.example.pidev.Dto.PostResponse;
import com.example.pidev.Exceptions.PostNotFoundException;
import com.example.pidev.Exceptions.SubredditNotFoundException;

import com.example.pidev.Mapper.PostMapper;
import com.example.pidev.Service.Interface.ISecurity;
import com.example.pidev.Service.Interface.IUser;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
@Slf4j
@Transactional

public class PostService  {

    private final PostRepository postRepository;
    private final SubredditRepository subredditRepository;

    private final UserService userService;
    private  final UserRepositories userRepositories;
    @Autowired
    private final Security security;


    private final PostMapper postMapper;
    private final IUser iuser;
    private final ISecurity iSecurity;

    /*
    //Without User
    public void save(PostRequest postRequest) {

        Subreddit subreddit = subredditRepository.findByName(postRequest.getSubredditName())
                .orElseThrow(() -> new SubredditNotFoundException(postRequest.getSubredditName()));
        postRepository.save(postMapper.map(postRequest, subreddit ));
    } */

    public void save(PostRequest postRequest) {
        Subreddit subreddit = subredditRepository.findByName(postRequest.getSubredditName())
                .orElseThrow(() -> new SubredditNotFoundException(postRequest.getSubredditName()));
        User user = userRepositories.findByMail(security.getCurrentUserName());
       // User currentUser =   userRepositories.findByMail(iSecurity.getCurrentUser());
        postRepository.save(postMapper.map(postRequest, subreddit,user));
    };

    @Transactional(readOnly = true)
    public PostResponse getPost(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new PostNotFoundException(id.toString()));
        return postMapper.mapToDto(post);
    }

    @Transactional(readOnly = true)
    public List<PostResponse> getAllPosts() {
        return postRepository.findAll()
                .stream()
                .map(postMapper::mapToDto)
                .collect(toList());
    }

    public void deleteById(Long id) {
        postRepository.deleteById(id);
    }

    @Scheduled(cron = "0 0 0 * * ?") // run at midnight every day
    public void removeOldPosts() {
        LocalDateTime threshold = LocalDateTime.now().minusDays(15);
        Instant instant = threshold.atZone(ZoneId.systemDefault()).toInstant();
        List<Post> oldPosts = postRepository.findBycreatedDateBefore(instant);
        postRepository.deleteAll(oldPosts);

    }
/*
    public void Edit(PostRequest postRequest) {
        User user = userRepositories.findByMail(security.getCurrentUserName());
        Subreddit subreddit = subredditRepository.findByName(postRequest.getSubredditName())
                .orElseThrow(() -> new SubredditNotFoundException(postRequest.getSubredditName()));
        postRepository.save(postMapper.map(postRequest, subreddit,user));
    }
*/


 @Transactional(readOnly = true)
    public List<PostResponse> getPostsBySubreddit(Long subredditId) {
        Subreddit subreddit = subredditRepository.findById(subredditId)
                .orElseThrow(() -> new SubredditNotFoundException(subredditId.toString()));
        List<Post> posts = postRepository.findAllBySubreddit(subreddit);
        return posts.stream().map(postMapper::mapToDto).collect(toList());
    }


}