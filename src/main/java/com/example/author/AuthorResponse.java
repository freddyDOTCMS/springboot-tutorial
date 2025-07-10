package com.example.author;

/**
 * Data Transfer Object (DTO) for author responses in API endpoints.
 * 
 * <p>This class demonstrates the response DTO pattern in Spring Boot applications:</p>
 * <ul>
 *   <li><strong>Output DTO</strong>: This class represents the data structure returned to clients
 *       when they request author information. It contains only the fields that should be exposed
 *       in the API response.</li>
 *   <li><strong>JSON Serialization</strong>: When returned from controller methods, Spring will
 *       automatically serialize this object to JSON for the HTTP response.</li>
 *   <li><strong>API Contract</strong>: Defines the structure of JSON responses for author-related endpoints.</li>
 *   <li><strong>Data Hiding</strong>: Excludes internal fields like firstName and lastName, instead
 *       providing a computed fullName field for better API design.</li>
 * </ul>
 * 
 * <p>Key characteristics of this response DTO:</p>
 * <ul>
 *   <li><strong>Includes ID</strong>: Contains the generated ID for client reference</li>
 *   <li><strong>Computed fields</strong>: The fullName field is computed from firstName and lastName</li>
 *   <li><strong>Clean API</strong>: Provides a simplified view of the author data</li>
 *   <li><strong>Consistent structure</strong>: Maintains a consistent response format across all author endpoints</li>
 * </ul>
 * 
 * <p>Example JSON response:</p>
 * <pre>
 * {
 *   "id": 123,
 *   "fullName": "John Doe",
 *   "email": "john.doe@example.com"
 * }
 * </pre>
 * 
 * <p>This DTO is created by the AuthorMapper when converting Author entities to response objects,
 * ensuring that the API response structure is consistent and well-designed.</p>
 * 
 * @author Tutorial Application
 * @version 1.0
 * @since Spring Boot 3.x
 * @see AuthorMapper#toDto(Author)
 * @see AuthorController
 */
public class AuthorResponse {
    
    /**
     * The unique identifier of the author.
     * 
     * <p>This field is included in responses to allow clients to reference specific authors
     * in subsequent requests.</p>
     */
    private Long id;
    
    /**
     * The computed full name of the author.
     * 
     * <p>This field is computed by the AuthorMapper by combining firstName and lastName.
     * It provides a cleaner API response compared to exposing separate firstName and lastName fields.</p>
     */
    private String fullName;
    
    /**
     * The email address of the author.
     * 
     * <p>This field is included in responses to provide contact information for the author.</p>
     */
    private String email;

    /**
     * Gets the unique identifier of the author.
     * 
     * @return The author's ID
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the unique identifier of the author.
     * 
     * <p>This setter is used by the AuthorMapper when converting from Author entity to this response DTO.</p>
     * 
     * @param id The author's ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the computed full name of the author.
     * 
     * @return The author's full name
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * Sets the computed full name of the author.
     * 
     * <p>This setter is used by the AuthorMapper when converting from Author entity to this response DTO.
     * The fullName is computed by combining firstName and lastName.</p>
     * 
     * @param fullName The author's full name
     */
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    /**
     * Gets the email address of the author.
     * 
     * @return The author's email address
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email address of the author.
     * 
     * <p>This setter is used by the AuthorMapper when converting from Author entity to this response DTO.</p>
     * 
     * @param email The author's email address
     */
    public void setEmail(String email) {
        this.email = email;
    }
}
