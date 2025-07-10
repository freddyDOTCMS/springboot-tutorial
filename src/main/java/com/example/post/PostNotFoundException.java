package com.example.post;

import com.example.NotFoundEntityException;

/**
 * Specific exception thrown when a Post entity cannot be found in the system.
 * 
 * <p>This class demonstrates exception inheritance and specific exception handling patterns for Post entities:</p>
 * <ul>
 *   <li><strong>Inheritance</strong>: By extending NotFoundEntityException, this class inherits all its properties
 *       and behaviors. This follows the Object-Oriented Programming principle of inheritance, where a child class
 *       (PostNotFoundException) inherits from a parent class (NotFoundEntityException).</li>
 *   <li><strong>Specific Exception Types</strong>: Creating specific exceptions for different entity types allows
 *       for more precise exception handling and better code organization.</li>
 *   <li><strong>Consistent Error Handling</strong>: All post-related "not found" scenarios will throw this
 *       specific exception, which will be handled by the GlobalExceptionHandler.</li>
 * </ul>
 * 
 * <p>Benefits of using specific exceptions for different entity types:</p>
 * <ul>
 *   <li><strong>Precise Exception Handling</strong>: Allows for different handling strategies for different entity types</li>
 *   <li><strong>Better Code Readability</strong>: The exception name clearly indicates what type of entity is missing</li>
 *   <li><strong>Consistent Error Messages</strong>: Standardized error message formatting for all post-related scenarios</li>
 *   <li><strong>Debugging</strong>: Makes it easier to identify the source of "not found" errors</li>
 *   <li><strong>Exception Hierarchy</strong>: Demonstrates how to create a hierarchy of exceptions</li>
 * </ul>
 * 
 * <p>This exception is thrown by the PostService when:</p>
 * <ul>
 *   <li>A post is requested by ID but doesn't exist in the database</li>
 *   <li>A post is referenced in a relationship but the referenced post doesn't exist</li>
 *   <li>Attempting to retrieve a post that has been deleted</li>
 * </ul>
 * 
 * <p>When this exception is thrown, it will be caught by the GlobalExceptionHandler and converted
 * to an HTTP 404 (Not Found) response with the exception message.</p>
 * 
 * <p>This exception follows the same pattern as AuthorNotFoundException, demonstrating
 * consistent exception handling across different entity types in the application.</p>
 * 
 * @author Tutorial Application
 * @version 1.0
 * @since Spring Boot 3.x
 * @see NotFoundEntityException
 * @see GlobalExceptionHandler
 * @see PostService
 * @see AuthorNotFoundException
 */
public class PostNotFoundException extends NotFoundEntityException {

    /**
     * Constructs a new PostNotFoundException with a standardized error message.
     * 
     * <p>This constructor demonstrates:</p>
     * <ul>
     *   <li><strong>Super constructor call</strong>: Calls the parent class constructor with a formatted message</li>
     *   <li><strong>Standardized message format</strong>: Uses a consistent format for all post not found errors</li>
     *   <li><strong>Parameter inclusion</strong>: Includes the ID in the error message for debugging purposes</li>
     * </ul>
     * 
     * <p>The error message will be in the format: "Post not found with id: {id}"</p>
     * 
     * @param id The unique identifier of the post that could not be found
     */
    public PostNotFoundException(Long id) {
        super("Post not found with id: " + id);
    }
}
