package com.example.post.comment;

import com.example.post.Post;
import com.example.post.PostDetailResponse;
import com.example.post.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service class for Comment business logic and operations.
 * 
 * <p>This class demonstrates Spring Boot service layer patterns for comment management:</p>
 * <ul>
 *   <li><strong>@Service</strong>: Marks this class as a service component for business logic</li>
 *   <li><strong>@Autowired</strong>: Enables dependency injection of required components</li>
 *   <li><strong>Cross-service collaboration</strong>: Uses PostService to validate and retrieve posts</li>
 *   <li><strong>Data transformation</strong>: Uses CommentMapper to convert between entities and DTOs</li>
 * </ul>
 * 
 * <p>Key responsibilities of this service:</p>
 * <ul>
 *   <li><strong>Comment Management</strong>: Handles all comment-related business operations</li>
 *   <li><strong>Post Validation</strong>: Ensures comments are associated with valid posts</li>
 *   <li><strong>Data Transformation</strong>: Uses CommentMapper to convert between entities and DTOs</li>
 *   <li><strong>Cross-entity Operations</strong>: Collaborates with PostService for post validation</li>
 *   <li><strong>Transaction Management</strong>: Service methods are typically transactional</li>
 * </ul>
 * 
 * <p>This service demonstrates several Spring Boot best practices:</p>
 * <ul>
 *   <li><strong>Dependency Injection</strong>: Uses @Autowired for loose coupling</li>
 *   <li><strong>Service Collaboration</strong>: Services can inject and use other services</li>
 *   <li><strong>Data Access Abstraction</strong>: Uses repositories for data access</li>
 *   <li><strong>Business Logic Centralization</strong>: Contains comment-specific business rules</li>
 *   <li><strong>Exception Propagation</strong>: Proper exception handling and propagation</li>
 * </ul>
 * 
 * <p>The service layer acts as an intermediary between controllers and repositories,
 * containing business logic and orchestrating data operations for comments.</p>
 * 
 * @author Tutorial Application
 * @version 1.0
 * @since Spring Boot 3.x
 * @see CommentRepository
 * @see CommentMapper
 * @see PostService
 * @see CreateCommentRequest
 * @see CommentResponse
 */
@Service
public class CommentService {

    /**
     * Repository for Comment entity operations.
     * 
     * <p>This field demonstrates dependency injection with @Autowired for data access.</p>
     */
    @Autowired
    private CommentRepository commentRepository;

    /**
     * Service for Post operations.
     * 
     * <p>This field demonstrates cross-service collaboration, where the CommentService
     * injects and uses the PostService to validate that posts exist before creating comments.</p>
     */
    @Autowired
    private PostService postService;

    /**
     * Mapper for converting between Comment entities and DTOs.
     * 
     * <p>This field is used to transform data between the domain model (Comment entity)
     * and the API model (CommentResponse DTO).</p>
     */
    @Autowired
    private CommentMapper commentMapper;

    /**
     * Creates a new comment for a specific post and returns the response.
     * 
     * <p>This method demonstrates:</p>
     * <ul>
     *   <li><strong>Cross-service collaboration</strong>: Uses PostService to validate and retrieve the post</li>
     *   <li><strong>Data transformation</strong>: Converts CreateCommentRequest to Comment entity with post</li>
     *   <li><strong>Persistence</strong>: Saves the comment to the database</li>
     *   <li><strong>Response creation</strong>: Converts the saved entity back to a response DTO</li>
     *   <li><strong>Relationship management</strong>: Associates the comment with the specified post</li>
     * </ul>
     * 
     * <p>The method ensures that:</p>
     * <ul>
     *   <li>The post exists before creating the comment</li>
     *   <li>The comment is properly associated with the post</li>
     *   <li>The response includes all necessary comment information</li>
     * </ul>
     * 
     * <p>This method is used when creating new comments on posts, ensuring proper
     * validation and relationship management.</p>
     * 
     * @param createCommentRequest The request containing comment data to create
     * @param postId The unique identifier of the post to associate the comment with
     * @return The CommentResponse DTO representing the created comment
     * @throws PostNotFoundException if no post exists with the given ID
     */
    public CommentResponse save(final CreateCommentRequest createCommentRequest, final long postId) {
        final Post post = postService.get(postId);

        return commentMapper.toDto(commentRepository.save(
                commentMapper.toEntity(createCommentRequest, post))
        );
    }
}
