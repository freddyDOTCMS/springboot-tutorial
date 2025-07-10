package com.example.post.comment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * REST controller for handling HTTP requests related to Comment operations.
 * 
 * <p>This class demonstrates Spring Boot REST API development patterns for comment management:</p>
 * <ul>
 *   <li><strong>@RestController</strong>: Combines @Controller and @ResponseBody to handle web requests
 *       and automatically serialize return values to JSON for HTTP responses.</li>
 *   <li><strong>@RequestMapping("/api/posts")</strong>: Establishes the base URL "/api/posts" for all
 *       endpoints in this controller, following REST conventions for nested resources.</li>
 *   <li><strong>Nested Resource Design</strong>: Comments are treated as nested resources under posts,
 *       following REST API best practices for hierarchical relationships.</li>
 * </ul>
 * 
 * <p>This controller provides the following REST endpoints:</p>
 * <ul>
 *   <li><strong>POST /api/posts/{postId}/comments</strong>: Create a new comment for a specific post</li>
 * </ul>
 * 
 * <p>Key Spring Boot concepts demonstrated:</p>
 * <ul>
 *   <li><strong>Dependency Injection</strong>: Uses @Autowired to inject the CommentService</li>
 *   <li><strong>Nested Resource URLs</strong>: Comments are accessed through their parent post</li>
 *   <li><strong>Path Variables</strong>: Using @PathVariable to extract URL parameters</li>
 *   <li><strong>Request Body</strong>: Using @RequestBody to deserialize JSON request bodies</li>
 *   <li><strong>REST Conventions</strong>: Following RESTful API design principles</li>
 * </ul>
 * 
 * <p>This controller follows the nested resource pattern where comments are always
 * accessed in the context of their parent post, reflecting the real-world relationship
 * between posts and comments.</p>
 * 
 * @author Tutorial Application
 * @version 1.0
 * @since Spring Boot 3.x
 * @see CommentService
 * @see GlobalExceptionHandler
 */
@RestController
@RequestMapping("/api/posts")
public class CommentController {

    /**
     * Service layer component that contains the business logic for comment operations.
     * 
     * <p>This field demonstrates dependency injection with @Autowired:</p>
     * <ul>
     *   <li><strong>@Autowired</strong>: Tells Spring to automatically inject an instance of CommentService</li>
     *   <li><strong>Field injection</strong>: Spring will set this field when creating the controller bean</li>
     *   <li><strong>Loose coupling</strong>: The controller depends on the service interface, not implementation</li>
     * </ul>
     * 
     * <p>This follows the dependency injection principle, which is a key feature of the Spring framework
     * that promotes loose coupling and testability.</p>
     */
    @Autowired
    private CommentService commentService;

    /**
     * Creates a new comment for a specific post.
     * 
     * <p>This method demonstrates:</p>
     * <ul>
     *   <li><strong>@PostMapping("/{postId}/comments")</strong>: Maps HTTP POST requests to "/api/posts/{postId}/comments"
     *       where {postId} is a path variable that will be replaced with the actual post ID value. This follows
     *       REST conventions for nested resources.</li>
     *   <li><strong>@PathVariable("postId")</strong>: Binds the {postId} path variable from the URL to the postId parameter.
     *       Spring will automatically convert the string value to a long primitive.</li>
     *   <li><strong>@RequestBody</strong>: Binds the HTTP request body (JSON) to the CreateCommentRequest parameter.
     *       Spring will automatically deserialize the JSON to a CreateCommentRequest object.</li>
     *   <li><strong>Nested resource pattern</strong>: Comments are created in the context of their parent post.</li>
     * </ul>
     * 
     * <p>Example URL: POST /api/posts/123/comments</p>
     * 
     * <p>Example request body:</p>
     * <pre>
     * {
     *   "text": "This is a great post! Thanks for sharing."
     * }
     * </pre>
     * 
     * <p>HTTP Response:</p>
     * <ul>
     *   <li><strong>Status Code</strong>: 200 OK (or 201 Created in some implementations)</li>
     *   <li><strong>Content-Type</strong>: application/json</li>
     *   <li><strong>Body</strong>: JSON object representing the created comment (including generated ID)</li>
     * </ul>
     * 
     * <p>This endpoint demonstrates proper REST API design by:</p>
     * <ul>
     *   <li><strong>Hierarchical URLs</strong>: Comments are nested under posts</li>
     *   <li><strong>Resource relationships</strong>: Clear parent-child relationship in the URL structure</li>
     *   <li><strong>Consistent patterns</strong>: Follows the same patterns as other REST endpoints</li>
     *   <li><strong>Proper HTTP methods</strong>: Uses POST for creating new resources</li>
     * </ul>
     * 
     * @param postId The unique identifier of the post to create the comment for
     * @param request The CreateCommentRequest DTO containing the comment data to create
     * @return The created comment as a CommentResponse DTO
     * @throws PostNotFoundException if no post with the specified ID exists
     */
    @PostMapping("/{postId}/comments")
    public CommentResponse createComment(@PathVariable("postId") long postId,
                                         @RequestBody CreateCommentRequest request) {

        return commentService.save(request, postId);
    }
}
