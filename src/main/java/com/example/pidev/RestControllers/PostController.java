package com.example.pidev.RestControllers;

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
import com.example.pidev.Service.Classe.PostService;
import com.example.pidev.Service.Classe.Security;
import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.ResponseEntity.status;


@RestController
@RequestMapping("/api/posts")
@AllArgsConstructor
public class    PostController {

    private final PostService postService;
    private final PostRepository postRepository;
    private final PostMapper postMapper;
    private final SubredditRepository subredditRepository;
    private final Security security;
    private final UserRepositories userRepositories;

    @PostMapping
    public ResponseEntity<Void> createPost(@RequestBody PostRequest postRequest) {
        postService.save(postRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<PostResponse>> getAllPosts() {
        return status(HttpStatus.OK).body(postService.getAllPosts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostResponse> getPost(@PathVariable Long id) {
        return status(HttpStatus.OK).body(postService.getPost(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        postService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
/*
    @PutMapping("/{id}")
    public ResponseEntity<Void> editPost(@RequestBody PostRequest postRequest,@PathVariable Long id) {
        postService.getPost(id);
        postService.Edit(postRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }
*/

    @PutMapping("/{id}")
    public void updatePost(@PathVariable(value = "id") Long postId, @RequestBody PostRequest postRequest) throws PostNotFoundException {



        Post existingPost = postRepository.findById(postId)
                .orElseThrow(() -> new PostNotFoundException("Post not found for this id :: " + postId));

        Subreddit subreddit = subredditRepository.findByName(postRequest.getSubredditName())
                .orElseThrow(() -> new SubredditNotFoundException(postRequest.getSubredditName()));

        User user = userRepositories.findByMail(security.getCurrentUserName());
       // postRepository.save(postMapper.map(postRequest, subreddit, user));

        existingPost.setPostName(postRequest.getPostName());
        existingPost.setUrl(postRequest.getUrl());
        existingPost.setDescription(postRequest.getDescription());


        postMapper.map(postRequest, subreddit, user);

        final Post updatedPosts = postRepository.save(existingPost);

    };

}