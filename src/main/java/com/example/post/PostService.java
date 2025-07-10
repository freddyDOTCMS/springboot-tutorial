package com.example.post;

import com.example.author.Author;
import com.example.author.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Service class for Post business logic and operations.
 * 
 * <p>This class demonstrates advanced Spring Boot service layer patterns:</p>
 * <ul>
 *   <li><strong>@Service</strong>: Marks this class as a service component for business logic</li>
 *   <li><strong>@Autowired</strong>: Enables dependency injection of required components</li>
 *   <li><strong>@Qualifier</strong>: Specifies which implementation to inject when multiple beans exist</li>
 *   <li><strong>Cross-service collaboration</strong>: Demonstrates how services can collaborate with each other</li>
 * </ul>
 * 
 * <p>Key responsibilities of this service:</p>
 * <ul>
 *   <li><strong>Post Management</strong>: Handles all post-related business operations</li>
 *   <li><strong>Data Transformation</strong>: Uses mappers to convert between entities and DTOs</li>
 *   <li><strong>Cross-entity Operations</strong>: Collaborates with AuthorService for author validation</li>
 *   <li><strong>Exception Handling</strong>: Throws appropriate exceptions for error scenarios</li>
 *   <li><strong>Transaction Management</strong>: Service methods are typically transactional</li>
 * </ul>
 * 
 * <p>This service demonstrates several Spring Boot best practices:</p>
 * <ul>
 *   <li><strong>Dependency Injection</strong>: Uses @Autowired for loose coupling</li>
 *   <li><strong>Bean Qualification</strong>: Uses @Qualifier to specify exact bean implementations</li>
 *   <li><strong>Service Collaboration</strong>: Services can inject and use other services</li>
 *   <li><strong>Exception Propagation</strong>: Proper exception handling and propagation</li>
 *   <li><strong>Data Access Abstraction</strong>: Uses repositories for data access</li>
 * </ul>
 * 
 * <p>The service layer acts as an intermediary between controllers and repositories,
 * containing business logic and orchestrating data operations.</p>
 * 
 * @author Tutorial Application
 * @version 1.0
 * @since Spring Boot 3.x
 * @see PostRepository
 * @see PostMapper
 * @see PostDetailMapper
 * @see AuthorService
 * @see PostNotFoundException
 */
@Service
public class PostService {
    
    /**
     * Repository for Post entity operations.
     * 
     * <p>This field demonstrates basic dependency injection with @Autowired.</p>
     */
    @Autowired
    private PostRepository postRepository;

    /**
     * Mapper for converting between Post entities and DTOs.
     * 
     * <p>This field demonstrates dependency injection with @Qualifier:</p>
     * <ul>
     *   <li><strong>@Qualifier("postMapperImpl")</strong>: Specifies which implementation to inject
     *       when multiple beans of the same type exist. This is useful when you have multiple
     *       implementations of the same interface.</li>
     *   <li><strong>MapStruct Implementation</strong>: The "postMapperImpl" is the generated implementation
     *       of the PostMapper interface by MapStruct.</li>
     * </ul>
     * 
     * <p>@Qualifier is particularly useful when:</p>
     * <ul>
     *   <li>Multiple implementations of the same interface exist</li>
     *   <li>You need to specify which exact implementation to use</li>
     *   <li>Working with generated code (like MapStruct implementations)</li>
     * </ul>
     */
    @Autowired
    @Qualifier("postMapperImpl")
    private PostMapper postMapper;

    /**
     * Mapper for converting Post entities to detailed response DTOs.
     * 
     * <p>This mapper is used for creating detailed post responses that include
     * additional information like comments and author details.</p>
     */
    @Autowired
    private PostDetailMapper postDetailMapper;

    /**
     * Service for Author operations.
     * 
     * <p>This field demonstrates cross-service collaboration, where one service
     * injects and uses another service. This is a common pattern in Spring Boot
     * applications for handling complex business logic that spans multiple entities.</p>
     */
    @Autowired
    private AuthorService authorService;

    /**
     * Retrieves all posts and converts them to response DTOs.
     * 
     * <p>This method demonstrates:</p>
     * <ul>
     *   <li><strong>Repository usage</strong>: Uses postRepository.findAll() to get all posts</li>
     *   <li><strong>Stream processing</strong>: Uses Java streams to process the list of posts</li>
     *   <li><strong>Method reference</strong>: Uses postMapper::toDto as a method reference</li>
     *   <li><strong>Data transformation</strong>: Converts Post entities to PostResponse DTOs</li>
     * </ul>
     * 
     * @return A list of PostResponse objects representing all posts
     */
    public List<PostResponse> getAll() {
        return postRepository.findAll().stream()
                .map(postMapper::toDto)
                .toList();
    }

    /**
     * Retrieves a post by ID and converts it to a detailed response DTO.
     * 
     * <p>This method demonstrates:</p>
     * <ul>
     *   <li><strong>Exception handling</strong>: Throws PostNotFoundException if post is not found</li>
     *   <li><strong>Detailed mapping</strong>: Uses PostDetailMapper for comprehensive response</li>
     *   <li><strong>Method composition</strong>: Calls get(postId) and then maps the result</li>
     * </ul>
     * 
     * @param postId The unique identifier of the post to retrieve
     * @return The PostDetailResponse DTO if found
     * @throws PostNotFoundException if no post exists with the given ID
     */
    public PostDetailResponse getResponse(final long postId) throws PostNotFoundException {

        return postDetailMapper.toDto(get(postId));
    }

    /**
     * Retrieves a post by ID, throwing an exception if not found.
     * 
     * <p>This method demonstrates:</p>
     * <ul>
     *   <li><strong>Optional handling</strong>: Uses orElseThrow() to handle the case where a post is not found</li>
     *   <li><strong>Exception throwing</strong>: Throws PostNotFoundException when the post doesn't exist</li>
     *   <li><strong>Lambda expressions</strong>: Uses a lambda to create the exception</li>
     * </ul>
     * 
     * @param postId The unique identifier of the post to retrieve
     * @return The Post entity if found
     * @throws PostNotFoundException if no post exists with the given ID
     */
    public Post get(final long postId) throws PostNotFoundException {
        return postRepository.findById(postId)
                .orElseThrow(() -> new PostNotFoundException(postId));
    }

    /**
     * Creates a new post from the provided request and returns the response.
     * 
     * <p>This method demonstrates:</p>
     * <ul>
     *   <li><strong>Cross-service collaboration</strong>: Uses AuthorService to validate and retrieve the author</li>
     *   <li><strong>Data transformation</strong>: Converts CreatePostRequest to Post entity with author</li>
     *   <li><strong>Persistence</strong>: Saves the post to the database</li>
     *   <li><strong>Response creation</strong>: Converts the saved entity back to a response DTO</li>
     * </ul>
     * 
     * <p>The method ensures that the author exists before creating the post,
     * demonstrating proper validation and error handling.</p>
     * 
     * @param request The request containing post data to create
     * @return The PostResponse DTO representing the created post
     */
    public PostResponse save(final CreatePostRequest request) {
        Author author = getAuthorById(request.getAuthorId());
        Post post = postMapper.toEntity(request, author);
        return postMapper.toDto(postRepository.save(post));
    }

    /**
     * Retrieves an author by ID using the AuthorService.
     * 
     * <p>This method demonstrates cross-service collaboration:</p>
     * <ul>
     *   <li><strong>Service delegation</strong>: Delegates author retrieval to AuthorService</li>
     *   <li><strong>Error propagation</strong>: Any exceptions from AuthorService will be propagated</li>
     *   <li><strong>Separation of concerns</strong>: Each service handles its own domain</li>
     * </ul>
     * 
     * <p>This method is protected to allow for potential overriding in subclasses
     * while still maintaining encapsulation.</p>
     * 
     * @param id The unique identifier of the author to retrieve
     * @return The Author entity if found
     * @throws AuthorNotFoundException if no author exists with the given ID
     */
    protected Author getAuthorById(Long id) {
        return authorService.get(id);
    }
}
