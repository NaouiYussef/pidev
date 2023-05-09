package com.example.pidev.Service.Classe;


import com.example.pidev.Dto.DAO.Entities.Post;
import com.example.pidev.Dto.DAO.Entities.User;
import com.example.pidev.Dto.DAO.Entities.Vote;
import com.example.pidev.Dto.DAO.Repositories.PostRepository;
import com.example.pidev.Dto.DAO.Repositories.UserRepositories;
import com.example.pidev.Dto.DAO.Repositories.VoteRepository;
import com.example.pidev.Dto.VoteDto;
import com.example.pidev.Exceptions.PostNotFoundException;
import com.example.pidev.Exceptions.SpringRedditException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static com.example.pidev.Dto.DAO.Entities.VoteType.UPVOTE;


@Service
@AllArgsConstructor
public class VoteService {

    private final VoteRepository voteRepository;
    private final PostRepository postRepository;
    private  Security security;
   // private final AuthService authService;
    private  final UserRepositories userRepositories;

 @Transactional
    public void vote(VoteDto voteDto) {
        Post post = postRepository.findById(voteDto.getPostId())
                .orElseThrow(() -> new PostNotFoundException("Post Not Found with ID - " + voteDto.getPostId()));
        User user = userRepositories.findByMail(security.getCurrentUserName());
        //Optional<Vote> voteByPostAndUser = voteRepository.findTopByPostAndUserOrderByVoteIdDesc(post, user);
       Optional<Vote> voteByPostAndUser = voteRepository.findTopByPostAndUserOrderByVoteIdDesc(post,user);
        if (voteByPostAndUser.isPresent() &&
                voteByPostAndUser.get().getVoteType()
                        .equals(voteDto.getVoteType())) {
            throw new SpringRedditException("You have already "
                    + voteDto.getVoteType() + "'d for this post");
        }
        if (UPVOTE.equals(voteDto.getVoteType())) {
            post.setVoteCount(post.getVoteCount() + 1);
        } else {
            post.setVoteCount(post.getVoteCount() - 1);
        }
        voteRepository.save(mapToVote(voteDto, post));
        postRepository.save(post);
    }
/*
    private Vote mapToVote(VoteDto voteDto, Post post) {
        return Vote.builder()
                .voteType(voteDto.getVoteType())
                .post(post)
                .build();
    }
*/


    private Vote mapToVote(VoteDto voteDto, Post post) {
        User user = userRepositories.findByMail(security.getCurrentUserName());
        return Vote.builder()
                .voteType(voteDto.getVoteType())
                .post(post)
                .user(user)
                .build();
    }


}
