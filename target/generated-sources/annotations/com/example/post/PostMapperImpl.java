package com.example.post;

import com.example.author.Author;
import com.example.author.AuthorMapper;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-07-09T19:47:09-0600",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 22.0.1 (Amazon.com Inc.)"
)
@Component
public class PostMapperImpl implements PostMapper {

    @Autowired
    private AuthorMapper authorMapper;

    @Override
    public Post toEntity(CreatePostRequest request, Author author) {
        if ( request == null && author == null ) {
            return null;
        }

        Post post = new Post();

        if ( request != null ) {
            post.setTitle( request.getTitle() );
            post.setContent( request.getContent() );
            post.setExcerpt( request.getExcerpt() );
        }
        post.setAuthor( author );

        return post;
    }

    @Override
    public PostResponse toDto(Post post) {
        if ( post == null ) {
            return null;
        }

        PostResponse postResponse = new PostResponse();

        if ( post.getId() != null ) {
            postResponse.setId( String.valueOf( post.getId() ) );
        }
        postResponse.setTitle( post.getTitle() );
        postResponse.setContent( post.getContent() );
        postResponse.setExcerpt( post.getExcerpt() );
        postResponse.setAuthor( authorMapper.toDto( post.getAuthor() ) );

        return postResponse;
    }
}
