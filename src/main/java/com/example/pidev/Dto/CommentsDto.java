package com.example.pidev.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentsDto {
    private Long id;
    private Long postId;
    private Long parentCommentId;
    private Instant createdDate;
    private String text;
    private String userName;
    private String postName;
    private String duration;
}