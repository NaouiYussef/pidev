package com.example.pidev.Mapper;

import com.example.pidev.DAO.Entities.Comment;
import com.example.pidev.DAO.Entities.Post;
import com.example.pidev.DAO.Entities.User;
import com.example.pidev.Dto.CommentsDto;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-05-10T11:00:14+0100",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 1.8.0_60 (Oracle Corporation)"
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
            comment.setParentCommentId( commentsDto.getParentCommentId() );
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

        commentsDto.setUserName( commentUserUsername( comment ) );
        commentsDto.setId( comment.getId() );
        commentsDto.setCreatedDate( comment.getCreatedDate() );
        commentsDto.setText( comment.getText() );

        commentsDto.setPostId( comment.getPost().getPostId() );
        commentsDto.setPostName( comment.getPost().getPostName() );
        commentsDto.setDuration( getDuration(commentsDto) );
        commentsDto.setParentCommentId( comment.getParentCommentId() );

        return commentsDto;
    }

    private String commentUserUsername(Comment comment) {
        if ( comment == null ) {
            return null;
        }
        User user = comment.getUser();
        if ( user == null ) {
            return null;
        }
        String username = user.getUsername();
        if ( username == null ) {
            return null;
        }
        return username;
    }
}
