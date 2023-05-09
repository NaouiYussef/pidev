package com.example.pidev.Mapper;

import com.example.pidev.Dto.CommentsDto;
import com.example.pidev.Dto.DAO.Entities.Comment;
import com.example.pidev.Dto.DAO.Entities.Post;
import com.example.pidev.Dto.DAO.Entities.User;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-05-05T19:57:09+0100",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 1.8.0_311 (Oracle Corporation)"
)
@Component
public class CommentMapperImpl implements CommentMapper {

    @Override
    public Comment map(CommentsDto commentsDto, Post post, User user) {
        if ( commentsDto == null && post == null && user == null ) {
            return null;
        }

        Comment comment = new Comment();

        if ( commentsDto != null ) {
            comment.setText( commentsDto.getText() );
        }
        comment.setPost( post );
        comment.setUser( user );
        comment.setCreatedDate( java.time.Instant.now() );

        return comment;
    }

    @Override
    public CommentsDto mapToDto(Comment comment) {
        if ( comment == null ) {
            return null;
        }

        CommentsDto commentsDto = new CommentsDto();

        commentsDto.setId( comment.getId() );
        commentsDto.setCreatedDate( comment.getCreatedDate() );
        commentsDto.setText( comment.getText() );

        commentsDto.setPostId( comment.getPost().getPostId() );
        commentsDto.setPostName( comment.getPost().getPostName() );
        commentsDto.setDuration( getDuration(commentsDto) );
        commentsDto.setUserName( comment.getUser().getUsername() );

        return commentsDto;
    }
}
