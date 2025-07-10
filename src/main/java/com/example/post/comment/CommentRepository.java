package com.example.post.comment;

import com.example.author.Author;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for Comment entity operations using Spring Data JPA.
 * 
 * <p>This interface demonstrates Spring Data JPA repository concepts for comment management:</p>
 * <ul>
 *   <li><strong>JpaRepository extension</strong>: By extending JpaRepository, this interface automatically
 *       provides basic CRUD operations (Create, Read, Update, Delete) for the Comment entity without
 *       requiring any implementation code.</li>
 *   <li><strong>Generic parameters</strong>: JpaRepository&lt;Comment, Long&gt; specifies that this repository
 *       works with Comment entities and uses Long as the primary key type.</li>
 *   <li><strong>Automatic implementation</strong>: Spring Boot will automatically create an implementation
 *       of this interface at runtime, so you don't need to write any implementation code.</li>
 * </ul>
 * 
 * <p>By extending JpaRepository, this repository automatically provides the following methods:</p>
 * <ul>
 *   <li><strong>save(Comment entity)</strong>: Saves or updates a comment</li>
 *   <li><strong>findById(Long id)</strong>: Finds a comment by ID</li>
 *   <li><strong>findAll()</strong>: Returns all comments</li>
 *   <li><strong>delete(Comment entity)</strong>: Deletes a comment</li>
 *   <li><strong>count()</strong>: Returns the total number of comments</li>
 *   <li><strong>existsById(Long id)</strong>: Checks if a comment exists by ID</li>
 *   <li>And many more...</li>
 * </ul>
 * 
 * <p>Key benefits of Spring Data JPA repositories:</p>
 * <ul>
 *   <li><strong>No boilerplate code</strong>: No need to implement basic CRUD operations</li>
 *   <li><strong>Type safety</strong>: Compile-time checking of entity and ID types</li>
 *   <li><strong>Automatic transaction management</strong>: Methods are automatically transactional</li>
 *   <li><strong>Query method support</strong>: Can add custom query methods by following naming conventions</li>
 * </ul>
 * 
 * <p>This repository is automatically detected by Spring Boot's component scanning and will be
 * available for dependency injection in other components like services and controllers.</p>
 * 
 * <p>Note: This repository could be extended with custom query methods for comment-specific operations,
 * such as finding comments by post ID or author, if needed for the application.</p>
 * 
 * @author Tutorial Application
 * @version 1.0
 * @since Spring Boot 3.x
 * @see Comment
 * @see org.springframework.data.jpa.repository.JpaRepository
 */
public interface CommentRepository extends JpaRepository<Comment, Long> {
}
