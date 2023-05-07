package com.example.pidev.Mapper;

import com.example.pidev.DAO.Entities.Comment;
import com.example.pidev.DAO.Entities.Post;
import com.example.pidev.DAO.Entities.User;
import com.example.pidev.Dto.CommentsDto;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-05-06T19:41:59+0100",
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
            comment.setSubComments( commentsDtoListToCommentList( commentsDto.getSubComments() ) );
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
        commentsDto.setSubComments( commentListToCommentsDtoList( comment.getSubComments() ) );

        commentsDto.setPostId( comment.getPost().getPostId() );
        commentsDto.setPostName( comment.getPost().getPostName() );
        commentsDto.setDuration( getDuration(commentsDto) );

        return commentsDto;
    }

    protected List<Comment> commentsDtoListToCommentList(List<CommentsDto> list) {
        if ( list == null ) {
            return null;
        }

        List<Comment> list1 = new ArrayList<Comment>( list.size() );
        for ( CommentsDto commentsDto : list ) {
            list1.add( commentsDtoToComment( commentsDto ) );
        }

        return list1;
    }

    protected Comment commentsDtoToComment(CommentsDto commentsDto) {
        if ( commentsDto == null ) {
            return null;
        }

        Comment comment = new Comment();

        comment.setId( commentsDto.getId() );
        comment.setText( commentsDto.getText() );
        comment.setCreatedDate( commentsDto.getCreatedDate() );
        comment.setSubComments( commentsDtoListToCommentList( commentsDto.getSubComments() ) );

        return comment;
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

    protected List<CommentsDto> commentListToCommentsDtoList(List<Comment> list) {
        if ( list == null ) {
            return null;
        }

        List<CommentsDto> list1 = new ArrayList<CommentsDto>( list.size() );
        for ( Comment comment : list ) {
            list1.add( mapToDto( comment ) );
        }

        return list1;
    }
}
