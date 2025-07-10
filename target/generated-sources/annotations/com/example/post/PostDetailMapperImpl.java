package com.example.post;

import com.example.author.Author;
import com.example.author.AuthorMapper;
import com.example.post.comment.Comment;
import com.example.post.comment.CommentMapper;
import com.example.post.comment.CommentResponse;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-07-09T19:47:09-0600",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 22.0.1 (Amazon.com Inc.)"
)
@Component
public class PostDetailMapperImpl implements PostDetailMapper {

    @Autowired
    private AuthorMapper authorMapper;
    @Autowired
    private CommentMapper commentMapper;

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
    public PostDetailResponse toDto(Post post) {
        if ( post == null ) {
            return null;
        }

        PostDetailResponse postDetailResponse = new PostDetailResponse();

        postDetailResponse.setComments( commentListToCommentResponseList( post.getComments() ) );
        if ( post.getId() != null ) {
            postDetailResponse.setId( String.valueOf( post.getId() ) );
        }
        postDetailResponse.setTitle( post.getTitle() );
        postDetailResponse.setContent( post.getContent() );
        postDetailResponse.setExcerpt( post.getExcerpt() );
        postDetailResponse.setAuthor( authorMapper.toDto( post.getAuthor() ) );

        return postDetailResponse;
    }

    protected List<CommentResponse> commentListToCommentResponseList(List<Comment> list) {
        if ( list == null ) {
            return null;
        }

        List<CommentResponse> list1 = new ArrayList<CommentResponse>( list.size() );
        for ( Comment comment : list ) {
            list1.add( commentMapper.toDto( comment ) );
        }

        return list1;
    }
}
