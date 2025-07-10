package com.example.post;

import com.example.author.Author;
import com.example.post.comment.Comment;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

/**
 * JPA entity class representing a Post in the tutorial application.
 * 
 * <p>This class demonstrates advanced JPA concepts including entity relationships:</p>
 * <ul>
 *   <li><strong>@Entity</strong>: Marks this class as a JPA entity that will be mapped to a database table.</li>
 *   <li><strong>@OneToMany</strong>: Establishes a one-to-many relationship with Comment entities.</li>
 *   <li><strong>@ManyToOne</strong>: Establishes a many-to-one relationship with Author entities.</li>
 *   <li><strong>Cascade Operations</strong>: Demonstrates how to configure automatic cascading of operations.</li>
 * </ul>
 * 
 * <p>This entity demonstrates complex JPA relationships:</p>
 * <ul>
 *   <li><strong>One-to-Many with Comments</strong>: One post can have multiple comments</li>
 *   <li><strong>Many-to-One with Author</strong>: Multiple posts can be written by one author</li>
 *   <li><strong>Cascade Operations</strong>: Operations on posts automatically affect related entities</li>
 *   <li><strong>Orphan Removal</strong>: Comments are automatically deleted when removed from a post</li>
 * </ul>
 * 
 * <p>The Post entity will be stored in a database table with the following structure:</p>
 * <ul>
 *   <li>id (BIGINT, PRIMARY KEY, AUTO_INCREMENT)</li>
 *   <li>title (VARCHAR)</li>
 *   <li>content (TEXT)</li>
 *   <li>excerpt (VARCHAR)</li>
 *   <li>author_id (BIGINT, FOREIGN KEY to author table)</li>
 * </ul>
 * 
 * <p>Related tables:</p>
 * <ul>
 *   <li><strong>comment table</strong>: Contains comments with a post_id foreign key</li>
 *   <li><strong>author table</strong>: Referenced by the author_id foreign key</li>
 * </ul>
 * 
 * <p>This entity is used throughout the application to represent blog posts that can have
 * multiple comments and are written by authors.</p>
 * 
 * @author Tutorial Application
 * @version 1.0
 * @since Spring Boot 3.x
 * @see Author
 * @see Comment
 * @see jakarta.persistence.OneToMany
 * @see jakarta.persistence.ManyToOne
 */
@Entity
public class Post {

    /**
     * The unique identifier for the post.
     * 
     * <p>This field demonstrates JPA primary key configuration:</p>
     * <ul>
     *   <li><strong>@Id</strong>: Marks this field as the primary key of the entity</li>
     *   <li><strong>@GeneratedValue(strategy = GenerationType.IDENTITY)</strong>: Specifies that the database
     *       should automatically generate this value using an auto-increment strategy</li>
     * </ul>
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The title of the post.
     * 
     * <p>This field will be mapped to a database column named "title".</p>
     */
    private String title;

    /**
     * The main content of the post.
     * 
     * <p>This field will be mapped to a database column named "content" and is typically
     * stored as TEXT to accommodate longer content.</p>
     */
    private String content;

    /**
     * A short summary or preview of the post content.
     * 
     * <p>This field will be mapped to a database column named "excerpt" and is used
     * for displaying post previews without loading the full content.</p>
     */
    private String excerpt;

    /**
     * The list of comments associated with this post.
     * 
     * <p>This field demonstrates a one-to-many relationship with Comment entities:</p>
     * <ul>
     *   <li><strong>@OneToMany(mappedBy = "post")</strong>: Establishes a one-to-many relationship where
     *       the Comment entity has a "post" field that owns this relationship. This means one post
     *       can have multiple comments.</li>
     *   <li><strong>cascade = CascadeType.ALL</strong>: Operations performed on a Post (like save or delete)
     *       will be automatically cascaded to its associated Comments. This means when you save a post,
     *       all its comments will also be saved.</li>
     *   <li><strong>orphanRemoval = true</strong>: If a Comment is removed from the comments list,
     *       it will be automatically deleted from the database. This prevents orphaned comments.</li>
     * </ul>
     * 
     * <p>The relationship is bidirectional, meaning the Comment entity also has a reference back to this Post.</p>
     */
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();

    /**
     * The author of the post.
     * 
     * <p>This field demonstrates a many-to-one relationship with Author entities:</p>
     * <ul>
     *   <li><strong>@ManyToOne(cascade = CascadeType.ALL)</strong>: Establishes a many-to-one relationship
     *       where multiple posts can be written by one author. The cascade setting means operations
     *       on posts will also affect the associated author.</li>
     * </ul>
     * 
     * <p>This relationship creates a foreign key in the post table that references the author table.</p>
     */
    @ManyToOne(cascade = CascadeType.ALL)
    private Author author;

    /**
     * Sets the title of the post.
     * 
     * @param title The title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Sets the content of the post.
     * 
     * @param content The content to set
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * Sets the author of the post.
     * 
     * <p>This method establishes the many-to-one relationship between this post and the specified author.
     * When this post is saved, JPA will automatically create the foreign key relationship.</p>
     * 
     * @param author The author to set
     */
    public void setAuthor(Author author) {
        this.author = author;
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
     * Gets the content of the post.
     * 
     * @return The post's content
     */
    public String getContent() {
        return content;
    }

    /**
     * Gets the author of the post.
     * 
     * @return The post's author
     */
    public Author getAuthor() {
        return author;
    }

    /**
     * Sets the ID of the post.
     * 
     * <p>Note: In most cases, you won't need to set the ID manually as it's
     * generated by the database. This method exists mainly for framework use and testing.</p>
     * 
     * @param id The post's unique identifier
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the ID of the post.
     * 
     * @return The post's unique identifier
     */
    public Long getId() {
        return id;
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
     * @param excerpt The excerpt to set
     */
    public void setExcerpt(String excerpt) {
        this.excerpt = excerpt;
    }

    /**
     * Adds a comment to this post.
     * 
     * <p>This method adds a comment to the post's list of comments. Due to the
     * @OneToMany relationship and cascade settings, the comment will be
     * automatically persisted when the post is saved.</p>
     * 
     * <p>This method demonstrates proper relationship management in JPA.</p>
     * 
     * @param comment The comment to add to this post
     */
    public void addComment(Comment comment) {
        comments.add(comment);
    }

    /**
     * Gets all comments for this post.
     * 
     * @return A list of all comments associated with this post
     */
    public List<Comment> getComments() {
        return comments;
    }

    /**
     * Sets the list of comments for this post.
     * 
     * <p>Note: This replaces the entire list of comments. If you want to add a
     * single comment, use the addComment method instead.</p>
     * 
     * @param comments The list of comments to set
     */
    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}
