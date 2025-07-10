package com.example;

/**
 * Base exception class for all "not found" scenarios in the tutorial application.
 * 
 * <p>This exception demonstrates proper exception handling patterns in Spring Boot applications:</p>
 * <ul>
 *   <li><strong>RuntimeException extension</strong>: By extending RuntimeException, this becomes an unchecked exception.
 *       This means methods don't need to explicitly declare that they throw this exception, reducing boilerplate code.</li>
 *   <li><strong>Centralized exception handling</strong>: When this exception is thrown, it will be caught by the 
 *       GlobalExceptionHandler and converted into an appropriate HTTP response (typically 404 Not Found).</li>
 *   <li><strong>Consistent error responses</strong>: This ensures that all "not found" scenarios return consistent
 *       error responses across the entire application.</li>
 * </ul>
 * 
 * <p>In Spring Boot applications, it's a best practice to:</p>
 * <ul>
 *   <li>Create specific exception types for different error scenarios</li>
 *   <li>Use unchecked exceptions for business logic errors</li>
 *   <li>Handle exceptions centrally with @ControllerAdvice</li>
 *   <li>Return consistent error responses</li>
 * </ul>
 * 
 * <p>This exception is used throughout the application when entities (authors, posts, comments)
 * cannot be found in the database.</p>
 * 
 * @author Tutorial Application
 * @version 1.0
 * @since Spring Boot 3.x
 * @see GlobalExceptionHandler
 */
public class NotFoundEntityException extends RuntimeException {

    /**
     * Constructs a new NotFoundEntityException with the specified detail message.
     * 
     * <p>This constructor demonstrates proper exception creation with meaningful error messages.
     * The message should clearly indicate what entity was not found and provide context
     * for debugging purposes.</p>
     * 
     * @param message A descriptive message explaining what entity was not found and why.
     *               Example: "Author with ID 123 not found" or "Post with title 'My Post' not found"
     */
    public NotFoundEntityException(String message) {
        super(message);
    }
}
