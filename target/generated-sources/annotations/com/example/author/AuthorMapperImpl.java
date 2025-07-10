package com.example.author;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-07-09T19:47:09-0600",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 22.0.1 (Amazon.com Inc.)"
)
@Component
public class AuthorMapperImpl extends AuthorMapper {

    @Override
    public Author toEntity(CreateAuthorRequest request) {
        if ( request == null ) {
            return null;
        }

        Author author = new Author();

        author.setFirstName( request.getFirstName() );
        author.setLastName( request.getLastName() );
        author.setEmail( request.getEmail() );

        return author;
    }

    @Override
    public AuthorResponse toDto(Author author) {
        if ( author == null ) {
            return null;
        }

        AuthorResponse authorResponse = new AuthorResponse();

        authorResponse.setId( author.getId() );
        authorResponse.setEmail( author.getEmail() );

        authorResponse.setFullName( getFullName(author) );

        return authorResponse;
    }
}
