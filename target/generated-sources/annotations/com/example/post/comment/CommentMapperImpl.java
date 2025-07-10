package com.example.post.comment;

import com.example.post.Post;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-07-09T19:47:09-0600",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 22.0.1 (Amazon.com Inc.)"
)
@Component
public class CommentMapperImpl extends CommentMapper {

    @Override
    public Comment toEntity(CreateCommentRequest request, Post post) {
        if ( request == null && post == null ) {
            return null;
        }

        Comment comment = new Comment();

        if ( request != null ) {
            comment.setText( request.getText() );
        }
        comment.setPost( post );

        return comment;
    }

    @Override
    public CommentResponse toDto(Comment comment) {
        if ( comment == null ) {
            return null;
        }

        CommentResponse commentResponse = new CommentResponse();

        commentResponse.setId( comment.getId() );
        commentResponse.setText( comment.getText() );

        return commentResponse;
    }
}
