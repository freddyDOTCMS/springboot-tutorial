package com.example.post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for handling HTTP requests related to Post operations.
 * 
 * <p>This class demonstrates Spring Boot REST API development patterns for post management:</p>
 * <ul>
 *   <li><strong>@RestController</strong>: Combines @Controller and @ResponseBody to handle web requests
 *       and automatically serialize return values to JSON for HTTP responses.</li>
 *   <li><strong>@RequestMapping("/api/posts")</strong>: Establishes the base URL "/api/posts" for all
 *       endpoints in this controller.</li>
 *   <li><strong>REST API Design</strong>: Follows RESTful conventions with proper HTTP methods and status codes.</li>
 * </ul>
 * 
 * <p>This controller provides the following REST endpoints:</p>
 * <ul>
 *   <li><strong>GET /api/posts</strong>: Retrieve all posts</li>
 *   <li><strong>GET /api/posts/{id}</strong>: Retrieve a specific post by ID with detailed information</li>
 *   <li><strong>POST /api/posts</strong>: Create a new post</li>
 * </ul>
 * 
 * <p>Key Spring Boot concepts demonstrated:</p>
 * <ul>
 *   <li><strong>Dependency Injection</strong>: Uses @Autowired to inject the PostService</li>
 *   <li><strong>Request Mapping</strong>: Various @GetMapping and @PostMapping annotations</li>
 *   <li><strong>Path Variables</strong>: Using @PathVariable to extract URL parameters</li>
 *   <li><strong>Request Body</strong>: Using @RequestBody to deserialize JSON request bodies</li>
 *   <li><strong>Exception Handling</strong>: Exceptions are handled by GlobalExceptionHandler</li>
 *   <li><strong>Response Types</strong>: Different response DTOs for different use cases</li>
 * </ul>
 * 
 * <p>This controller follows the layered architecture pattern where controllers handle HTTP concerns,
 * services contain business logic, and repositories handle data access.</p>
 * 
 * @author Tutorial Application
 * @version 1.0
 * @since Spring Boot 3.x
 * @see PostService
 * @see GlobalExceptionHandler
 */
@RestController
@RequestMapping("/api/posts")
public class PostController {

    /**
     * Service layer component that contains the business logic for post operations.
     * 
     * <p>This field demonstrates dependency injection with @Autowired:</p>
     * <ul>
     *   <li><strong>@Autowired</strong>: Tells Spring to automatically inject an instance of PostService</li>
     *   <li><strong>Field injection</strong>: Spring will set this field when creating the controller bean</li>
     *   <li><strong>Loose coupling</strong>: The controller depends on the service interface, not implementation</li>
     * </ul>
     * 
     * <p>This follows the dependency injection principle, which is a key feature of the Spring framework
     * that promotes loose coupling and testability.</p>
     */
    @Autowired
    private PostService postService;

    /**
     * Retrieves all posts from the system.
     * 
     * <p>This method demonstrates:</p>
     * <ul>
     *   <li><strong>@GetMapping</strong>: Maps HTTP GET requests to "/api/posts" to this method.
     *       The empty parentheses indicate that this handles requests to the base path.</li>
     *   <li><strong>Return type</strong>: Returns List&lt;PostResponse&gt; which will be automatically
     *       serialized to JSON and sent as the HTTP response body.</li>
     *   <li><strong>Service delegation</strong>: Delegates the business logic to the PostService.</li>
     * </ul>
     * 
     * <p>HTTP Response:</p>
     * <ul>
     *   <li><strong>Status Code</strong>: 200 OK (default for successful GET requests)</li>
     *   <li><strong>Content-Type</strong>: application/json</li>
     *   <li><strong>Body</strong>: JSON array of post objects</li>
     * </ul>
     * 
     * @return A list of all posts as PostResponse DTOs
     */
    @GetMapping
    public List<PostResponse> getPosts() {
        return postService.getAll();
    }

    /**
     * Retrieves a specific post by ID with detailed information.
     * 
     * <p>This method demonstrates:</p>
     * <ul>
     *   <li><strong>@GetMapping("/{id}")</strong>: Maps HTTP GET requests to "/api/posts/{id}" where {id}
     *       is a path variable that will be replaced with the actual ID value.</li>
     *   <li><strong>@PathVariable("id")</strong>: Binds the {id} path variable from the URL to the id parameter.
     *       Spring will automatically convert the string value to a long primitive.</li>
     *   <li><strong>Detailed response</strong>: Returns PostDetailResponse which includes additional information
     *       like comments and author details.</li>
     *   <li><strong>Exception handling</strong>: If the post is not found, PostNotFoundException will be thrown
     *       and handled by GlobalExceptionHandler to return a 404 Not Found response.</li>
     * </ul>
     * 
     * <p>Example URL: GET /api/posts/123</p>
     * 
     * <p>HTTP Response:</p>
     * <ul>
     *   <li><strong>Status Code</strong>: 200 OK if found, 404 Not Found if not found</li>
     *   <li><strong>Content-Type</strong>: application/json</li>
     *   <li><strong>Body</strong>: JSON object representing the post with detailed information</li>
     * </ul>
     * 
     * @param id The unique identifier of the post to retrieve
     * @return The post as a PostDetailResponse DTO with detailed information
     * @throws PostNotFoundException if no post with the specified ID exists
     */
    @GetMapping(value = "/{id}")
    public PostDetailResponse getPost(@PathVariable("id") long id) {
        return postService.getResponse(id);
    }

    /**
     * Creates a new post in the system.
     * 
     * <p>This method demonstrates:</p>
     * <ul>
     *   <li><strong>@PostMapping</strong>: Maps HTTP POST requests to "/api/posts" to this method.
     *       POST is the appropriate HTTP method for creating new resources.</li>
     *   <li><strong>@RequestBody</strong>: Binds the HTTP request body (JSON) to the CreatePostRequest parameter.
     *       Spring will automatically deserialize the JSON to a CreatePostRequest object.</li>
     *   <li><strong>Cross-entity validation</strong>: The service layer validates that the referenced author exists.</li>
     *   <li><strong>Data transformation</strong>: The service layer handles converting the request DTO to an entity,
     *       persisting it, and converting the result back to a response DTO.</li>
     * </ul>
     * 
     * <p>Example request body:</p>
     * <pre>
     * {
     *   "title": "My First Post",
     *   "content": "This is the content of my first post.",
     *   "excerpt": "A brief summary of the post",
     *   "authorId": 123
     * }
     * </pre>
     * 
     * <p>HTTP Response:</p>
     * <ul>
     *   <li><strong>Status Code</strong>: 200 OK (or 201 Created in some implementations)</li>
     *   <li><strong>Content-Type</strong>: application/json</li>
     *   <li><strong>Body</strong>: JSON object representing the created post (including generated ID)</li>
     * </ul>
     * 
     * @param request The CreatePostRequest DTO containing the post data to create
     * @return The created post as a PostResponse DTO
     */
    @PostMapping
    public PostResponse createPost(@RequestBody CreatePostRequest request) {
        return postService.save(request);
    }
}
