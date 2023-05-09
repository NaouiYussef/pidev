package com.example.pidev.Mapper;


import com.example.pidev.Dto.DAO.Entities.Comment;
import com.example.pidev.Dto.DAO.Entities.Post;
import com.example.pidev.Dto.DAO.Entities.User;
import com.example.pidev.Dto.CommentsDto;
import com.github.marlonlom.utilities.timeago.TimeAgo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface CommentMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "text", source = "commentsDto.text")
    @Mapping(target = "createdDate", expression = "java(java.time.Instant.now())")
    @Mapping(target = "post", source = "post")
   @Mapping(target = "user", source = "user")
  // Comment map(CommentsDto commentsDto, Post post);
    Comment  map(CommentsDto commentsDto, Post post,User user);
    default String getDuration(CommentsDto commentsDto) {
        return TimeAgo.using(commentsDto.getCreatedDate().toEpochMilli());
    }
    @Mapping(target = "postId", expression = "java(comment.getPost().getPostId())")
    @Mapping(target = "postName", expression = "java(comment.getPost().getPostName())")
    @Mapping(target = "duration", expression = "java(getDuration(commentsDto))")
    @Mapping(target = "userName", expression = "java(comment.getUser().getUsername())")
    CommentsDto mapToDto(Comment comment);

}
