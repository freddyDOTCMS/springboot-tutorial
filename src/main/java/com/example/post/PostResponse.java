package com.example.post;

import com.example.author.AuthorResponse;
import com.example.post.comment.CommentResponse;

import java.util.List;

/**
 * Data Transfer Object (DTO) for basic post responses in API endpoints.
 * 
 * <p>This class demonstrates the response DTO pattern for basic post information:</p>
 * <ul>
 *   <li><strong>Output DTO</strong>: This class represents the data structure returned to clients
 *       when they request basic post information. It contains essential post data without comments.</li>
 *   <li><strong>JSON Serialization</strong>: When returned from controller methods, Spring will
 *       automatically serialize this object to JSON for the HTTP response.</li>
 *   <li><strong>Nested DTOs</strong>: Includes AuthorResponse to provide author information without
 *       exposing the full Author entity.</li>
 *   <li><strong>API Contract</strong>: Defines the structure of JSON responses for basic post endpoints.</li>
 * </ul>
 * 
 * <p>Key characteristics of this response DTO:</p>
 * <ul>
 *   <li><strong>Includes ID</strong>: Contains the generated ID for client reference</li>
 *   <li><strong>Complete post data</strong>: Title, content, and excerpt for full post information</li>
 *   <li><strong>Author information</strong>: Includes AuthorResponse for attribution</li>
 *   <li><strong>No comments</strong>: Excludes comments for basic responses (use PostDetailResponse for comments)</li>
 *   <li><strong>Clean API</strong>: Provides essential information without overwhelming detail</li>
 * </ul>
 * 
 * <p>Example JSON response:</p>
 * <pre>
 * {
 *   "id": "123",
 *   "title": "My First Blog Post",
 *   "content": "This is the content of my first blog post...",
 *   "excerpt": "A brief summary of the post content",
 *   "author": {
 *     "id": 456,
 *     "fullName": "John Doe",
 *     "email": "john.doe@example.com"
 *   }
 * }
 * </pre>
 * 
 * <p>This DTO is created by the PostMapper when converting Post entities to basic response objects.
 * It's used for endpoints that need post information but don't require the full detail including comments.</p>
 * 
 * <p>For detailed responses that include comments, use PostDetailResponse instead.</p>
 * 
 * @author Tutorial Application
 * @version 1.0
 * @since Spring Boot 3.x
 * @see PostMapper#toDto(Post)
 * @see PostDetailResponse
 * @see AuthorResponse
 */
public class PostResponse {
    
    /**
     * The unique identifier of the post.
     * 
     * <p>This field is included in responses to allow clients to reference specific posts
     * in subsequent requests.</p>
     */
    private String id;
    
    /**
     * The title of the post.
     * 
     * <p>This field provides the main headline or title of the post.</p>
     */
    private String title;
    
    /**
     * The main content of the post.
     * 
     * <p>This field contains the full text content of the post, which can be quite long
     * and contain multiple paragraphs.</p>
     */
    private String content;
    
    /**
     * A short summary or preview of the post content.
     * 
     * <p>This field is used for displaying post previews without loading the full content.
     * It provides a brief overview of what the post is about.</p>
     */
    private String excerpt;
    
    /**
     * The author information for the post.
     * 
     * <p>This field contains the author details as an AuthorResponse DTO, providing
     * attribution information without exposing the full Author entity structure.</p>
     */
    private AuthorResponse authorResponse;

    /**
     * Gets the unique identifier of the post.
     * 
     * @return The post's ID
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the unique identifier of the post.
     * 
     * <p>This setter is used by the PostMapper when converting from Post entity to this response DTO.</p>
     * 
     * @param id The post's ID
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Gets the title of the post.
     * 
     * @return The post's title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title of the post.
     * 
     * <p>This setter is used by the PostMapper when converting from Post entity to this response DTO.</p>
     * 
     * @param title The post's title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets the content of the post.
     * 
     * @return The post's content
     */
    public String getContent() {
        return content;
    }

    /**
     * Sets the content of the post.
     * 
     * <p>This setter is used by the PostMapper when converting from Post entity to this response DTO.</p>
     * 
     * @param content The post's content
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * Gets the excerpt of the post.
     * 
     * @return The post's excerpt
     */
    public String getExcerpt() {
        return excerpt;
    }

    /**
     * Sets the excerpt of the post.
     * 
     * <p>This setter is used by the PostMapper when converting from Post entity to this response DTO.</p>
     * 
     * @param excerpt The post's excerpt
     */
    public void setExcerpt(String excerpt) {
        this.excerpt = excerpt;
    }

    /**
     * Gets the author information for the post.
     * 
     * @return The author information as an AuthorResponse DTO
     */
    public AuthorResponse getAuthor() {
        return authorResponse;
    }

    /**
     * Sets the author information for the post.
     * 
     * <p>This setter is used by the PostMapper when converting from Post entity to this response DTO.
     * The AuthorResponse is created by the AuthorMapper from the Author entity.</p>
     * 
     * @param authorResponse The author information as an AuthorResponse DTO
     */
    public void setAuthor(AuthorResponse authorResponse) {
        this.authorResponse = authorResponse;
    }
}
