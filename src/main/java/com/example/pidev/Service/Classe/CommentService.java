package com.example.pidev.Service.Classe;


import com.example.pidev.DAO.Entities.Comment;
import com.example.pidev.DAO.Entities.Post;
import com.example.pidev.DAO.Entities.User;
import com.example.pidev.DAO.Repositories.CommentRepository;
import com.example.pidev.DAO.Repositories.PostRepository;
import com.example.pidev.DAO.Repositories.UserRepositories;
import com.example.pidev.Dto.CommentsDto;
import com.example.pidev.Dto.SubredditDto;
import com.example.pidev.Exceptions.PostNotFoundException;
import com.example.pidev.Mapper.CommentMapper;
import lombok.AllArgsConstructor;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.webjars.NotFoundException;

import java.time.Instant;
import java.util.*;

import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
public class CommentService {
    private static final String POST_URL = "";
    private final PostRepository postRepository;
    private final UserRepositories userRepository;
    List<String> badWords = Arrays.asList("shit", "fuck", "asshole");

    // private final AuthService authService;
    private final CommentMapper commentMapper;
    private final UserRepositories userRepositories;
    private final Security security;
    private final CommentRepository commentRepository;
   // private final MailContentBuilder mailContentBuilder;
   // private final MailService mailService;

    public void save(CommentsDto commentsDto) {
        Long postId = commentsDto.getPostId();
        if (postId == null) {
            throw new IllegalArgumentException("postId cannot be null");
        }
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new PostNotFoundException(postId.toString()));

        // String filteredComment = filterComment(commentsDto.getText());
        User user = userRepositories.findByMail(security.getCurrentUserName());
        Comment comment = commentMapper.map(commentsDto, post,user);
        //comment.setText(filteredComment);
        commentRepository.save(comment);



    // String message = mailContentBuilder.build(authService.getCurrentUser() + " posted a comment on your post." + POST_URL);
       // sendCommentNotification(message, post.getUser());
    }

    @Transactional(readOnly = true)
    public List<CommentsDto> getAll() {
        return commentRepository.findAll()
                .stream()
                .map(commentMapper::mapToDto)
                .collect(toList());
    }

    /*
    private void sendCommentNotification(String message, User user) {
        mailService.sendMail(new NotificationEmail(user.getUsername() + " Commented on your post", user.getEmail(), message));
    }
*/
    public List<CommentsDto> getAllCommentsForPost(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new PostNotFoundException(postId.toString()));
        return commentRepository.findByPost(post)
                .stream()
                .map(commentMapper::mapToDto).collect(toList());
    }

    public List<CommentsDto> getAllCommentsForUser(String userName) {
        User user = userRepository.findByUsername(userName)
                .orElseThrow(() -> new UsernameNotFoundException(userName));
        return commentRepository.findAllByUser(user)
                .stream()
                .map(commentMapper::mapToDto)
                .collect(toList());
    }

  public void deleteById(Long id) {
      commentRepository.deleteById(id);
  }

    public String filterComment(String comment) {
        String[] words = comment.split("\\s+"); // Split the comment into words
        int len = words.length;

        for (int i = 0; i < len; i++) {
            if (badWords.contains(words[i])) { // Check if word is bad
                System.out.println("Bad word found: " + words[i]);
                if (i >= 3) { // Check if index is within bounds
                    words[i-3] = words[i-3].replaceAll("\\w", "*"); // Replace last 3 words with '*'
                    words[i-2] = words[i-2].replaceAll("\\w", "*");
                    words[i-1] = words[i-1].replaceAll("\\w", "*");
                } else {
                    words[i] = words[i].replaceAll("\\w", "*"); // Replace the bad word with '*'
                }
            }
        }

        return String.join(" ", words); // Join the words back into a string
    }

    public CommentsDto createSubComment(Long commentId, CommentsDto commentDto) {
        Comment parentComment = commentRepository.findById(commentId)
                .orElseThrow(() -> new NotFoundException("Comment not found with id - " + commentId));

        User currentUser = userRepositories.findByMail(security.getCurrentUserName());

        Comment subComment = commentMapper.map(commentDto, parentComment.getPost(), currentUser);
        subComment.setParentComment(parentComment);
        subComment.setCreatedDate(Instant.now());

        parentComment.addSubComment(subComment); // Add the subcomment to the parent comment's list of subcomments

        Comment savedComment = commentRepository.save(subComment);
        CommentsDto savedCommentResponse = commentMapper.mapToDto(savedComment);

        return savedCommentResponse;
    }



    public List<CommentsDto> getAllSubCommentsByComment(Long commentId) {
        Comment parentComment = commentRepository.findById(commentId)
                .orElseThrow(() -> new NotFoundException("Comment not found with id - " + commentId));

        List<Comment> subComments = parentComment.getSubComments();
        List<CommentsDto> subCommentDtos = new ArrayList<>();
        for (Comment subComment : subComments) {
            subCommentDtos.add(commentMapper.mapToDto(subComment));
        }
        return subCommentDtos;
    }





}