package com.example.author;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for handling HTTP requests related to Author operations.
 * 
 * <p>This class demonstrates Spring Boot REST API development patterns:</p>
 * <ul>
 *   <li><strong>@RestController</strong>: This annotation combines @Controller and @ResponseBody.
 *       It indicates that this class handles web requests and that the return values of its methods
 *       should be automatically serialized to JSON and bound to the web response body.</li>
 *   <li><strong>@RequestMapping("/api/authors")</strong>: This annotation maps all requests to the "/api/authors"
 *       path to this controller. It establishes the base URL for all endpoints in this controller.</li>
 *   <li><strong>REST API Design</strong>: This controller follows RESTful API conventions with proper HTTP methods
 *       and status codes.</li>
 * </ul>
 * 
 * <p>This controller provides the following REST endpoints:</p>
 * <ul>
 *   <li><strong>GET /api/authors</strong>: Retrieve all authors</li>
 *   <li><strong>GET /api/authors/{id}</strong>: Retrieve a specific author by ID</li>
 *   <li><strong>POST /api/authors</strong>: Create a new author</li>
 * </ul>
 * 
 * <p>Key Spring Boot concepts demonstrated:</p>
 * <ul>
 *   <li><strong>Dependency Injection</strong>: Uses @Autowired to inject the AuthorService</li>
 *   <li><strong>Request Mapping</strong>: Various @GetMapping and @PostMapping annotations</li>
 *   <li><strong>Path Variables</strong>: Using @PathVariable to extract URL parameters</li>
 *   <li><strong>Request Body</strong>: Using @RequestBody to deserialize JSON request bodies</li>
 *   <li><strong>Exception Handling</strong>: Exceptions are handled by GlobalExceptionHandler</li>
 * </ul>
 * 
 * <p>This controller follows the layered architecture pattern where controllers handle HTTP concerns,
 * services contain business logic, and repositories handle data access.</p>
 * 
 * @author Tutorial Application
 * @version 1.0
 * @since Spring Boot 3.x
 * @see AuthorService
 * @see GlobalExceptionHandler
 */
@RestController
@RequestMapping("/api/authors")
public class AuthorController {

    /**
     * Service layer component that contains the business logic for author operations.
     * 
     * <p>This field demonstrates dependency injection with @Autowired:</p>
     * <ul>
     *   <li><strong>@Autowired</strong>: Tells Spring to automatically inject an instance of AuthorService</li>
     *   <li><strong>Field injection</strong>: Spring will set this field when creating the controller bean</li>
     *   <li><strong>Loose coupling</strong>: The controller depends on the service interface, not implementation</li>
     * </ul>
     * 
     * <p>This follows the dependency injection principle, which is a key feature of the Spring framework
     * that promotes loose coupling and testability.</p>
     */
    @Autowired
    private AuthorService authorService;


    /**
     * Retrieves all authors from the system.
     * 
     * <p>This method demonstrates:</p>
     * <ul>
     *   <li><strong>@GetMapping()</strong>: Maps HTTP GET requests to "/api/authors" to this method.
     *       The empty parentheses indicate that this handles requests to the base path.</li>
     *   <li><strong>Return type</strong>: Returns List&lt;AuthorResponse&gt; which will be automatically
     *       serialized to JSON and sent as the HTTP response body.</li>
     *   <li><strong>Service delegation</strong>: Delegates the business logic to the AuthorService.</li>
     * </ul>
     * 
     * <p>HTTP Response:</p>
     * <ul>
     *   <li><strong>Status Code</strong>: 200 OK (default for successful GET requests)</li>
     *   <li><strong>Content-Type</strong>: application/json</li>
     *   <li><strong>Body</strong>: JSON array of author objects</li>
     * </ul>
     * 
     * @return A list of all authors as AuthorResponse DTOs
     */
    @GetMapping()
    public List<AuthorResponse> getAuthors() {
        return authorService.getAll();
    }

    /**
     * Retrieves a specific author by their unique identifier.
     * 
     * <p>This method demonstrates:</p>
     * <ul>
     *   <li><strong>@GetMapping("/{id}")</strong>: Maps HTTP GET requests to "/api/authors/{id}" where {id}
     *       is a path variable that will be replaced with the actual ID value.</li>
     *   <li><strong>@PathVariable("id")</strong>: Binds the {id} path variable from the URL to the id parameter.
     *       Spring will automatically convert the string value to a long primitive.</li>
     *   <li><strong>Exception handling</strong>: If the author is not found, AuthorNotFoundException will be thrown
     *       and handled by GlobalExceptionHandler to return a 404 Not Found response.</li>
     * </ul>
     * 
     * <p>Example URL: GET /api/authors/123</p>
     * 
     * <p>HTTP Response:</p>
     * <ul>
     *   <li><strong>Status Code</strong>: 200 OK if found, 404 Not Found if not found</li>
     *   <li><strong>Content-Type</strong>: application/json</li>
     *   <li><strong>Body</strong>: JSON object representing the author</li>
     * </ul>
     * 
     * @param id The unique identifier of the author to retrieve
     * @return The author as an AuthorResponse DTO
     * @throws AuthorNotFoundException if no author with the specified ID exists
     */
    @GetMapping(value = "/{id}")
    public AuthorResponse getAuthor(@PathVariable("id") long id) {
        return authorService.getResponse(id);
    }

    /**
     * Creates a new author in the system.
     * 
     * <p>This method demonstrates:</p>
     * <ul>
     *   <li><strong>@PostMapping</strong>: Maps HTTP POST requests to "/api/authors" to this method.
     *       POST is the appropriate HTTP method for creating new resources.</li>
     *   <li><strong>@RequestBody</strong>: Binds the HTTP request body (JSON) to the CreateAuthorRequest parameter.
     *       Spring will automatically deserialize the JSON to a CreateAuthorRequest object.</li>
     *   <li><strong>Data transformation</strong>: The service layer handles converting the request DTO to an entity,
     *       persisting it, and converting the result back to a response DTO.</li>
     * </ul>
     * 
     * <p>Example request body:</p>
     * <pre>
     * {
     *   "firstName": "John",
     *   "lastName": "Doe",
     *   "email": "john.doe@example.com"
     * }
     * </pre>
     * 
     * <p>HTTP Response:</p>
     * <ul>
     *   <li><strong>Status Code</strong>: 200 OK (or 201 Created in some implementations)</li>
     *   <li><strong>Content-Type</strong>: application/json</li>
     *   <li><strong>Body</strong>: JSON object representing the created author (including generated ID)</li>
     * </ul>
     * 
     * @param request The CreateAuthorRequest DTO containing the author data to create
     * @return The created author as an AuthorResponse DTO
     */
    @PostMapping
    public AuthorResponse createAuthor(@RequestBody CreateAuthorRequest request) {
        return authorService.save(request);
    }
}
