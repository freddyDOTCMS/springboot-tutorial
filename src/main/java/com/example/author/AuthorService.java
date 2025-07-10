package com.example.author;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

/**
 * Service class for Author business logic and operations.
 * 
 * <p>This class demonstrates Spring Boot service layer patterns:</p>
 * <ul>
 *   <li><strong>@Service</strong>: This annotation indicates that this class is a service component.
 *       It's a specialized form of @Component that is used to mark classes that implement business logic.
 *       Spring will automatically detect this class and register it as a bean for dependency injection.</li>
 *   <li><strong>@Autowired</strong>: This annotation enables automatic dependency injection. Spring will
 *       automatically inject the required dependencies (AuthorRepository and AuthorMapper) when creating
 *       an instance of this service.</li>
 *   <li><strong>Service Layer Pattern</strong>: This class acts as an intermediary between controllers
 *       and repositories, containing business logic and orchestrating data operations.</li>
 * </ul>
 * 
 * <p>Key responsibilities of this service:</p>
 * <ul>
 *   <li><strong>Business Logic</strong>: Contains the business rules and logic for author operations</li>
 *   <li><strong>Data Transformation</strong>: Uses AuthorMapper to convert between entities and DTOs</li>
 *   <li><strong>Exception Handling</strong>: Throws appropriate exceptions when authors are not found</li>
 *   <li><strong>Transaction Management</strong>: Service methods are typically transactional</li>
 * </ul>
 * 
 * <p>This service demonstrates the layered architecture pattern where:</p>
 * <ul>
 *   <li>Controllers handle HTTP requests and responses</li>
 *   <li>Services contain business logic</li>
 *   <li>Repositories handle data access</li>
 *   <li>Mappers handle data transformation</li>
 * </ul>
 * 
 * @author Tutorial Application
 * @version 1.0
 * @since Spring Boot 3.x
 * @see AuthorRepository
 * @see AuthorMapper
 * @see AuthorNotFoundException
 */
@Service
public class AuthorService {

    /**
     * Repository for Author entity operations.
     * 
     * <p>This field demonstrates dependency injection with @Autowired:</p>
     * <ul>
     *   <li><strong>@Autowired</strong>: Tells Spring to automatically inject an implementation of AuthorRepository</li>
     *   <li><strong>Private field injection</strong>: Spring will set this field when creating the service bean</li>
     *   <li><strong>Type-based injection</strong>: Spring will find the AuthorRepository bean and inject it</li>
     * </ul>
     * 
     * <p>Alternative injection methods include constructor injection and setter injection,
     * but field injection is commonly used for simplicity.</p>
     */
    @Autowired
    private AuthorRepository authorRepository;

    /**
     * Mapper for converting between Author entities and DTOs.
     * 
     * <p>This field is used to transform data between the domain model (Author entity)
     * and the API model (AuthorResponse DTO).</p>
     */
    @Autowired
    private AuthorMapper authorMapper;

    /**
     * Retrieves all authors and converts them to response DTOs.
     * 
     * <p>This method demonstrates:</p>
     * <ul>
     *   <li><strong>Repository usage</strong>: Uses authorRepository.findAll() to get all authors</li>
     *   <li><strong>Stream processing</strong>: Uses Java streams to process the list of authors</li>
     *   <li><strong>Method reference</strong>: Uses authorMapper::toDto as a method reference</li>
     *   <li><strong>Data transformation</strong>: Converts Author entities to AuthorResponse DTOs</li>
     * </ul>
     * 
     * @return A list of AuthorResponse objects representing all authors
     */
    public List<AuthorResponse> getAll() {
        return authorRepository.findAll().stream()
                .map(authorMapper::toDto)
                .toList();
    }

    /**
     * Retrieves an author by ID, throwing an exception if not found.
     * 
     * <p>This method demonstrates:</p>
     * <ul>
     *   <li><strong>Optional handling</strong>: Uses orElseThrow() to handle the case where an author is not found</li>
     *   <li><strong>Exception throwing</strong>: Throws AuthorNotFoundException when the author doesn't exist</li>
     *   <li><strong>Lambda expressions</strong>: Uses a lambda to create the exception</li>
     * </ul>
     * 
     * @param id The unique identifier of the author to retrieve
     * @return The Author entity if found
     * @throws AuthorNotFoundException if no author exists with the given ID
     */
    public Author get( long id) {
        return authorRepository.findById(id).orElseThrow(() -> new AuthorNotFoundException(id));
    }

    /**
     * Retrieves an author by ID and converts it to a response DTO.
     * 
     * <p>This method demonstrates method composition by calling get(id) and then
     * converting the result to a DTO using the mapper.</p>
     * 
     * @param id The unique identifier of the author to retrieve
     * @return The AuthorResponse DTO if found
     * @throws AuthorNotFoundException if no author exists with the given ID
     */
    public AuthorResponse getResponse( long id) {
        return authorMapper.toDto(get(id));
    }

    /**
     * Creates a new author from the provided request and returns the response.
     * 
     * <p>This method demonstrates:</p>
     * <ul>
     *   <li><strong>Data transformation</strong>: Converts CreateAuthorRequest to Author entity</li>
     *   <li><strong>Persistence</strong>: Saves the author to the database</li>
     *   <li><strong>Response creation</strong>: Converts the saved entity back to a response DTO</li>
     * </ul>
     * 
     * @param createAuthorRequest The request containing author data to create
     * @return The AuthorResponse DTO representing the created author
     */
    public AuthorResponse save(final CreateAuthorRequest createAuthorRequest) {
        return authorMapper.toDto(
                authorRepository.save(authorMapper.toEntity(createAuthorRequest))
        );
    }
}
