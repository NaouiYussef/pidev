package com.example.pidev.Dto;

import com.example.pidev.DAO.Entities.Comment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentsDto {
    private Long id;
    private Long postId;
    private Instant createdDate;
    private String text;
    private String userName;
    private String postName;
    private String duration;
    private String parentComment;
    private List<CommentsDto> subComments;
}