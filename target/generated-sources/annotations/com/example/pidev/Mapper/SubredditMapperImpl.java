package com.example.pidev.Mapper;

import com.example.pidev.DAO.Entities.Subreddit;
import com.example.pidev.Dto.SubredditDto;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-05-08T00:20:04+0100",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 1.8.0_60 (Oracle Corporation)"
)
@Component
public class SubredditMapperImpl implements SubredditMapper {

    @Override
    public SubredditDto mapSubredditToDto(Subreddit subreddit) {
        if ( subreddit == null ) {
            return null;
        }

        SubredditDto subredditDto = new SubredditDto();

        subredditDto.setId( subreddit.getId() );
        subredditDto.setName( subreddit.getName() );
        subredditDto.setDescription( subreddit.getDescription() );

        subredditDto.setCreatedDate( java.time.Instant.now() );
        subredditDto.setNumberOfPosts( mapPosts(subreddit.getPosts()) );

        return subredditDto;
    }

    @Override
    public Subreddit mapDtoToSubreddit(SubredditDto subredditDto) {
        if ( subredditDto == null ) {
            return null;
        }

        Subreddit subreddit = new Subreddit();

        subreddit.setId( subredditDto.getId() );
        subreddit.setName( subredditDto.getName() );
        subreddit.setDescription( subredditDto.getDescription() );
        subreddit.setCreatedDate( subredditDto.getCreatedDate() );

        return subreddit;
    }
}
