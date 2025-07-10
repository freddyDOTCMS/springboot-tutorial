package com.example;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Global exception handler that provides centralized error handling for the entire application.
 * 
 * <p>This class demonstrates advanced Spring Boot exception handling patterns:</p>
 * <ul>
 *   <li><strong>@ControllerAdvice</strong>: This annotation allows you to handle exceptions across the whole application,
 *       not just in a specific controller. It's a specialized form of @Component that is designed to assist controllers.
 *       When an exception occurs in any controller, Spring will look for a matching exception handler in this class.</li>
 *   <li><strong>@ExceptionHandler</strong>: This annotation indicates that a method should handle exceptions of a specific type.
 *       It can be applied to methods in @ControllerAdvice classes to provide global exception handling.</li>
 * </ul>
 * 
 * <p>Benefits of centralized exception handling:</p>
 * <ul>
 *   <li><strong>Consistency</strong>: All similar exceptions are handled the same way across the application</li>
 *   <li><strong>Maintainability</strong>: Error handling logic is centralized in one place</li>
 *   <li><strong>Clean Controllers</strong>: Controllers can focus on business logic without exception handling boilerplate</li>
 *   <li><strong>Proper HTTP Status Codes</strong>: Ensures appropriate HTTP status codes are returned for different error types</li>
 * </ul>
 * 
 * <p>This tutorial demonstrates how to:</p>
 * <ul>
 *   <li>Set up global exception handling with @ControllerAdvice</li>
 *   <li>Handle specific exception types with @ExceptionHandler</li>
 *   <li>Return appropriate HTTP status codes and error messages</li>
 *   <li>Maintain clean separation between business logic and error handling</li>
 * </ul>
 * 
 * @author Tutorial Application
 * @version 1.0
 * @since Spring Boot 3.x
 * @see NotFoundEntityException
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handles all NotFoundEntityException exceptions thrown by the application.
     * 
     * <p>This method demonstrates proper exception handling with @ExceptionHandler:</p>
     * <ul>
     *   <li><strong>@ExceptionHandler(NotFoundEntityException.class)</strong>: This annotation tells Spring that this method
     *       should handle any exception of type NotFoundEntityException (or its subclasses) thrown anywhere in the application.</li>
     *   <li><strong>ResponseEntity</strong>: Returns a ResponseEntity which allows us to specify both the response body
     *       and the HTTP status code.</li>
     *   <li><strong>HttpStatus.NOT_FOUND</strong>: Returns HTTP 404 status code, which is the appropriate response
     *       for "not found" scenarios.</li>
     * </ul>
     * 
     * <p>When any controller throws a NotFoundEntityException, Spring will automatically call this method
     * and return the response to the client.</p>
     * 
     * @param ex The NotFoundEntityException that was thrown by the application
     * @return A ResponseEntity containing the exception message as the response body and HTTP 404 status code
     */
    @ExceptionHandler(NotFoundEntityException.class)
    public ResponseEntity<String> handleNotFoundException(final NotFoundEntityException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
}
