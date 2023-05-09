package com.example.pidev.RestControllers;

import com.example.pidev.DAO.Entities.Comment;
import com.example.pidev.Dto.CommentsDto;
import com.example.pidev.Dto.SubredditDto;
import com.example.pidev.Mapper.CommentMapper;
import com.example.pidev.Service.Classe.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.web.servlet.function.ServerResponse.status;

@RestController
@RequestMapping("/api/comments")
@AllArgsConstructor
public class CommentController {

    private final CommentService commentService;
    private  final CommentMapper commentMapper;

    @PostMapping
    public ResponseEntity<Void> createComment(@RequestBody CommentsDto commentsDto) {
        commentService.save(commentsDto);
        return new ResponseEntity<>(CREATED);
    }

    @GetMapping("/by-post/{postId}")
    public ResponseEntity<List<CommentsDto>> getAllCommentsForPost(@PathVariable Long postId) {
        return ResponseEntity.status(OK)
                .body(commentService.getAllCommentsForPost(postId));
    }
    /*
        @GetMapping("/by-user/{userName}")
        public ResponseEntity<List<CommentsDto>> getAllCommentsForUser(@PathVariable String userName){
            return ResponseEntity.status(OK)
                    .body(commentService.getAllCommentsForUser(userName));
        }
    */
    @GetMapping
    public ResponseEntity<List<CommentsDto>> getAllComments() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(commentService.getAll());
    }

    @DeleteMapping ("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        commentService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/{commentId}")
    public void  createSubComment(@PathVariable Long commentId, @RequestBody CommentsDto commentRequest){
        commentService.createSubComment(commentId,commentRequest);
    }

    @GetMapping("/{commentId}/subcomments")
    public ResponseEntity<List<CommentsDto>> getAllSubCommentsByComment(@PathVariable Long commentId) {
        List<CommentsDto> subComments = commentService.getAllSubCommentsByComment(commentId);
        return ResponseEntity.ok(subComments);
    }


}
