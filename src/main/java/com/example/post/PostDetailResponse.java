package com.example.post;

import com.example.post.comment.CommentResponse;

import java.util.List;

/**
 * Data Transfer Object (DTO) for detailed post responses that include comments.
 * 
 * <p>This class demonstrates the inheritance pattern for response DTOs:</p>
 * <ul>
 *   <li><strong>Inheritance</strong>: Extends PostResponse to inherit all basic post information
 *       while adding specialized behavior for detailed responses that include comments.</li>
 *   <li><strong>Composition over duplication</strong>: Reuses PostResponse fields instead of duplicating them.</li>
 *   <li><strong>Specialized responses</strong>: Provides comprehensive post information including comments.</li>
 *   <li><strong>API design</strong>: Demonstrates how to create different response types for different use cases.</li>
 * </ul>
 * 
 * <p>Key characteristics of this detailed response DTO:</p>
 * <ul>
 *   <li><strong>Inherits from PostResponse</strong>: Contains all basic post information (id, title, content, excerpt, author)</li>
 *   <li><strong>Includes comments</strong>: Adds a list of CommentResponse objects for complete post information</li>
 *   <li><strong>Comprehensive data</strong>: Provides everything needed for detailed post views</li>
 *   <li><strong>Performance consideration</strong>: Used when comments are needed, which may be more expensive to load</li>
 *   <li><strong>Clean separation</strong>: Distinguishes between basic and detailed responses</li>
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
 *   },
 *   "comments": [
 *     {
 *       "id": "789",
 *       "content": "Great post!",
 *       "authorName": "Jane Smith"
 *     },
 *     {
 *       "id": "790",
 *       "content": "Very informative.",
 *       "authorName": "Bob Johnson"
 *     }
 *   ]
 * }
 * </pre>
 * 
 * <p>This DTO is created by the PostDetailMapper when converting Post entities to detailed response objects.
 * It's used for endpoints that need complete post information including comments, such as detailed post views.</p>
 * 
 * <p>The inheritance pattern demonstrates good API design by:</p>
 * <ul>
 *   <li><strong>Avoiding duplication</strong>: Reuses PostResponse fields</li>
 *   <li><strong>Maintaining consistency</strong>: Basic and detailed responses share the same structure for common fields</li>
 *   <li><strong>Providing flexibility</strong>: Allows different endpoints to return different levels of detail</li>
 *   <li><strong>Supporting performance optimization</strong>: Basic responses are faster to generate</li>
 * </ul>
 * 
 * @author Tutorial Application
 * @version 1.0
 * @since Spring Boot 3.x
 * @see PostResponse
 * @see PostDetailMapper
 * @see CommentResponse
 */
public class PostDetailResponse extends PostResponse {
    
    /**
     * The list of comments associated with this post.
     * 
     * <p>This field contains all comments for the post as CommentResponse DTOs, providing
     * complete comment information without exposing the full Comment entity structure.</p>
     * 
     * <p>The comments are loaded eagerly by the PostRepository using @EntityGraph to avoid
     * the N+1 query problem when retrieving posts with comments.</p>
     */
    private List<CommentResponse> comments;

    /**
     * Gets the list of comments for this post.
     * 
     * @return A list of CommentResponse DTOs representing all comments for this post
     */
    public List<CommentResponse> getComments() {
        return comments;
    }

    /**
     * Sets the list of comments for this post.
     * 
     * <p>This setter is used by the PostDetailMapper when converting from Post entity to this response DTO.
     * Each Comment entity is converted to a CommentResponse DTO by the CommentMapper.</p>
     * 
     * @param comments The list of CommentResponse DTOs representing all comments for this post
     */
    public void setComments(List<CommentResponse> comments) {
        this.comments = comments;
    }
}
