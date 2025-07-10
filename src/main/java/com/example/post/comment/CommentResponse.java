package com.example.post.comment;

/**
 * Data Transfer Object (DTO) for comment responses in API endpoints.
 * 
 * <p>This class demonstrates the response DTO pattern for comment information:</p>
 * <ul>
 *   <li><strong>Output DTO</strong>: This class represents the data structure returned to clients
 *       when they request comment information. It contains only the fields that should be exposed
 *       in the API response.</li>
 *   <li><strong>JSON Serialization</strong>: When returned from controller methods, Spring will
 *       automatically serialize this object to JSON for the HTTP response.</li>
 *   <li><strong>API Contract</strong>: Defines the structure of JSON responses for comment-related endpoints.</li>
 *   <li><strong>Clean design</strong>: Provides essential comment information without exposing internal details.</li>
 * </ul>
 * 
 * <p>Key characteristics of this response DTO:</p>
 * <ul>
 *   <li><strong>Includes ID</strong>: Contains the generated ID for client reference</li>
 *   <li><strong>Comment content</strong>: Includes the text content of the comment</li>
 *   <li><strong>No post reference</strong>: Excludes the post relationship to avoid circular references</li>
 *   <li><strong>Simple structure</strong>: Provides only essential comment information</li>
 *   <li><strong>Consistent format</strong>: Maintains a consistent response format across all comment endpoints</li>
 * </ul>
 * 
 * <p>Example JSON response:</p>
 * <pre>
 * {
 *   "id": 789,
 *   "text": "This is a great post! Thanks for sharing your thoughts."
 * }
 * </pre>
 * 
 * <p>This DTO is created by the CommentMapper when converting Comment entities to response objects.
 * It's used for endpoints that need comment information without the full post context.</p>
 * 
 * <p>The design avoids circular references by not including the post relationship in the response.
 * The post information is available through the parent post's detailed response when needed.</p>
 * 
 * @author Tutorial Application
 * @version 1.0
 * @since Spring Boot 3.x
 * @see CommentMapper#toDto(Comment)
 * @see CommentController
 */
public class CommentResponse {
    
    /**
     * The unique identifier of the comment.
     * 
     * <p>This field is included in responses to allow clients to reference specific comments
     * in subsequent requests.</p>
     */
    private Long id;
    
    /**
     * The text content of the comment.
     * 
     * <p>This field contains the actual comment text that was written by the user.
     * It can contain multiple sentences and paragraphs.</p>
     */
    private String text;

    /**
     * Gets the unique identifier of the comment.
     * 
     * @return The comment's ID
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the unique identifier of the comment.
     * 
     * <p>This setter is used by the CommentMapper when converting from Comment entity to this response DTO.</p>
     * 
     * @param id The comment's ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the text content of the comment.
     * 
     * @return The comment's text content
     */
    public String getText() {
        return text;
    }

    /**
     * Sets the text content of the comment.
     * 
     * <p>This setter is used by the CommentMapper when converting from Comment entity to this response DTO.</p>
     * 
     * @param text The comment's text content
     */
    public void setText(String text) {
        this.text = text;
    }
}
