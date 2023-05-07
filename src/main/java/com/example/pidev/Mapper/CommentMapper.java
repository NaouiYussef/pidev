package com.example.pidev.Mapper;


import com.example.pidev.DAO.Entities.Comment;
import com.example.pidev.DAO.Entities.Post;
import com.example.pidev.DAO.Entities.User;
import com.example.pidev.Dto.CommentsDto;
import com.github.marlonlom.utilities.timeago.TimeAgo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


@Mapper(componentModel = "spring")
public interface CommentMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "text", source = "commentsDto.text")
    @Mapping(target = "createdDate", expression = "java(java.time.Instant.now())")
    @Mapping(target = "post", source = "post")
   @Mapping(target = "user", source = "user")
   // @Mapping(target = "subComments", qualifiedByName = "mapSubComments")
  // Comment map(CommentsDto commentsDto, Post post);
    Comment  map(CommentsDto commentsDto, Post post,User user);
  //  Comment mapSubComment(CommentsDto commentsDto, Post post,User user,Comment parentComment);
    default String getDuration(CommentsDto commentsDto) {
        return TimeAgo.using(commentsDto.getCreatedDate().toEpochMilli());
    }

    @Named("mapSubComments")
    default List<Comment> mapSubComments(List<Comment> subComments) {
        return subComments.stream()
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }
    @Mapping(target = "postId", expression = "java(comment.getPost().getPostId())")
    @Mapping(target = "postName", expression = "java(comment.getPost().getPostName())")
    @Mapping(target = "duration", expression = "java(getDuration(commentsDto))")
    @Mapping(target = "userName", source = "user.username")
    CommentsDto mapToDto(Comment comment);

}
