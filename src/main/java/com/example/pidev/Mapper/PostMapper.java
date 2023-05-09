package com.example.pidev.Mapper;

import com.example.pidev.Dto.DAO.Entities.*;
import com.example.pidev.Dto.DAO.Repositories.CommentRepository;
import com.example.pidev.Dto.DAO.Repositories.UserRepositories;
import com.example.pidev.Dto.DAO.Repositories.VoteRepository;
import com.example.pidev.Dto.PostRequest;
import com.example.pidev.Dto.PostResponse;
import com.example.pidev.Service.Classe.Security;
import com.github.marlonlom.utilities.timeago.TimeAgo;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

import static com.example.pidev.Dto.DAO.Entities.VoteType.DOWNVOTE;
import static com.example.pidev.Dto.DAO.Entities.VoteType.UPVOTE;


@Mapper(componentModel = "spring")
public abstract class PostMapper {

    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private VoteRepository voteRepository;

 @Autowired
    private  UserRepositories userRepo;

    private Security security;
    /*@Autowired
    private AuthService authService;*/

    @Mapping(target = "createdDate", expression = "java(java.time.Instant.now())")
    @Mapping(target = "description", source = "postRequest.description")
    @Mapping(target = "subreddit", source = "subreddit")
    @Mapping(target = "voteCount", constant = "0")
    @Mapping(target = "user", source = "user")
    public abstract Post map(PostRequest postRequest, Subreddit subreddit, User user);


    //  public abstract Post map(PostRequest postRequest, Subreddit subreddit,User  user);

    //public abstract Post map(PostRequest postRequest, Subreddit subreddit);
   /*@AfterMapping
   protected void setPostUser(@MappingTarget Post post, String userName) {
       User user = userRepo.findByUsername(userName)
               .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + userName));
       post.setUser(user);
   }*/
    @Mapping(target = "id", source = "postId")
    @Mapping(target = "subredditName", source = "subreddit.name")
    @Mapping(target = "userName", source = "user.username")
    @Mapping(target = "commentCount", expression = "java(commentCount(post))")
    @Mapping(target = "duration", expression = "java(getDuration(post))")
   @Mapping(target = "upVote", expression = "java(isPostUpVoted(post))")
    @Mapping(target = "downVote", expression = "java(isPostDownVoted(post))")
    public abstract PostResponse mapToDto(Post post);

    Integer commentCount(Post post) {
        return commentRepository.findByPost(post).size();
    }

    String getDuration(Post post) {
        return TimeAgo.using(post.getCreatedDate().toEpochMilli());
    }

    boolean isPostUpVoted(Post post) {
        return checkVoteType(post, UPVOTE);
    }

    boolean isPostDownVoted(Post post) {
        return checkVoteType(post, DOWNVOTE);
    }


    private boolean checkVoteType(Post post, VoteType voteType) {
        if (post == null) {
            throw new IllegalArgumentException("post cannot be null");
        }

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            return false;
        }

        User user = userRepo.findByMail(authentication.getName());
        if (user == null) {
            return false;
        }

        Optional<Vote> voteForPostByUser = voteRepository.findTopByPostAndUserOrderByVoteIdDesc(post, user);
        return voteForPostByUser.filter(vote -> vote.getVoteType().equals(voteType)).isPresent();
    }









    /*

private boolean checkVoteType(Post post, VoteType voteType) {

        User user = userRepo.findByMail(security.getCurrentUserName()) ;
        Optional<Vote> voteForPostByUser = voteRepository.findTopByPostAndUserOrderByVoteIdDesc(post,user);
        return voteForPostByUser.filter(vote -> voteType != null && voteType.equals(vote.getVoteType())).isPresent();

        }
        */


   }












