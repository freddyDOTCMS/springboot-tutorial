package com.example.post;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * Repository interface for Post entity operations using Spring Data JPA.
 * 
 * <p>This interface demonstrates advanced Spring Data JPA concepts:</p>
 * <ul>
 *   <li><strong>JpaRepository extension</strong>: Provides basic CRUD operations for Post entities</li>
 *   <li><strong>@EntityGraph</strong>: Optimizes database queries by specifying which related entities
 *       should be eagerly loaded, preventing the N+1 query problem</li>
 *   <li><strong>Custom query methods</strong>: Overrides default repository methods with optimized implementations</li>
 * </ul>
 * 
 * <p>Key features demonstrated:</p>
 * <ul>
 *   <li><strong>Performance Optimization</strong>: Uses @EntityGraph to load related entities in a single query</li>
 *   <li><strong>N+1 Query Prevention</strong>: Avoids multiple database queries when loading posts with comments and authors</li>
 *   <li><strong>Eager Loading</strong>: Specifies which relationships should be loaded immediately</li>
 *   <li><strong>Method Overriding</strong>: Customizes default repository methods for specific use cases</li>
 * </ul>
 * 
 * <p>This repository addresses common JPA performance issues:</p>
 * <ul>
 *   <li><strong>Lazy Loading Issues</strong>: By default, JPA loads relationships lazily, which can cause
 *       LazyInitializationException when accessed outside a transaction</li>
 *   <li><strong>N+1 Query Problem</strong>: Without @EntityGraph, loading posts with comments would require
 *       separate queries for each post's comments</li>
 *   <li><strong>Eager Loading Control</strong>: @EntityGraph provides fine-grained control over what gets loaded</li>
 * </ul>
 * 
 * <p>The @EntityGraph annotation is particularly useful for:</p>
 * <ul>
 *   <li>Loading posts with their comments for display</li>
 *   <li>Loading posts with their authors for attribution</li>
 *   <li>Optimizing API responses that need related data</li>
 * </ul>
 * 
 * @author Tutorial Application
 * @version 1.0
 * @since Spring Boot 3.x
 * @see Post
 * @see org.springframework.data.jpa.repository.JpaRepository
 * @see org.springframework.data.jpa.repository.EntityGraph
 */
public interface PostRepository extends JpaRepository<Post, Long> {
    
    /**
     * Finds a post by ID with its comments eagerly loaded.
     * 
     * <p>This method demonstrates @EntityGraph for performance optimization:</p>
     * <ul>
     *   <li><strong>@EntityGraph(attributePaths = "comments")</strong>: Tells JPA to eagerly load
     *       the comments relationship when fetching the post. This prevents the N+1 query problem
     *       where each comment would require a separate database query.</li>
     *   <li><strong>Optional&lt;Post&gt;</strong>: Returns an Optional to handle the case where
     *       no post exists with the given ID, following modern Java practices.</li>
     *   <li><strong>Method Override</strong>: Overrides the default findById method from JpaRepository
     *       to provide optimized loading behavior.</li>
     * </ul>
     * 
     * <p>Performance benefits:</p>
     * <ul>
     *   <li><strong>Single Query</strong>: Loads the post and all its comments in one database query</li>
     *   <li><strong>No Lazy Loading Issues</strong>: Comments are available immediately, even outside transactions</li>
     *   <li><strong>Reduced Database Load</strong>: Eliminates multiple round trips to the database</li>
     * </ul>
     * 
     * @param id The unique identifier of the post to retrieve
     * @return An Optional containing the post with comments loaded, or empty if not found
     */
    @EntityGraph(attributePaths = "comments")
    Optional<Post> findById(Long id);

    /**
     * Retrieves all posts with their authors eagerly loaded.
     * 
     * <p>This method demonstrates @EntityGraph for bulk loading optimization:</p>
     * <ul>
     *   <li><strong>@EntityGraph(attributePaths = "author")</strong>: Tells JPA to eagerly load
     *       the author relationship for all posts. This is useful when displaying a list of posts
     *       where author information is needed.</li>
     *   <li><strong>List&lt;Post&gt;</strong>: Returns all posts with their authors loaded in a single query.</li>
     *   <li><strong>Bulk Optimization</strong>: Optimizes loading multiple posts with their authors.</li>
     * </ul>
     * 
     * <p>Use cases:</p>
     * <ul>
     *   <li><strong>Post Listing</strong>: Displaying a list of posts with author names</li>
     *   <li><strong>Dashboard Views</strong>: Showing posts with attribution</li>
     *   <li><strong>API Responses</strong>: Returning posts with author information</li>
     * </ul>
     * 
     * @return A list of all posts with their authors loaded
     */
    @EntityGraph(attributePaths = "author")
    List<Post> findAll();
}



