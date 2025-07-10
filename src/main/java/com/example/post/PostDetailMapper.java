package com.example.post;


import com.example.author.AuthorMapper;
import com.example.post.comment.CommentMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Specialized mapper for creating detailed post responses that include comments and author information.
 * 
 * <p>This interface demonstrates advanced MapStruct patterns including inheritance and complex nested mappings:</p>
 * <ul>
 *   <li><strong>Interface inheritance</strong>: Extends PostMapper to inherit basic mapping functionality
 *       while adding specialized behavior for detailed responses.</li>
 *   <li><strong>@Mapper(componentModel = "spring", uses = {AuthorMapper.class, CommentMapper.class})</strong>: 
 *       Tells MapStruct to generate a Spring component and use both AuthorMapper and CommentMapper for
 *       handling nested object mappings.</li>
 *   <li><strong>Method overriding</strong>: Overrides the toDto method to provide specialized mapping
 *       for detailed responses that include comments.</li>
 *   <li><strong>Multiple mapper dependencies</strong>: Uses both AuthorMapper and CommentMapper for
 *       complex nested object conversion.</li>
 * </ul>
 * 
 * <p>Key features demonstrated:</p>
 * <ul>
 *   <li><strong>Inheritance</strong>: Extends PostMapper to reuse basic mapping logic</li>
 *   <li><strong>Specialized responses</strong>: Creates detailed responses with comments included</li>
 *   <li><strong>Multiple dependencies</strong>: Uses multiple mappers for complex object graphs</li>
 *   <li><strong>Method overriding</strong>: Customizes mapping behavior for specific use cases</li>
 *   <li><strong>Complex relationships</strong>: Handles one-to-many relationships (posts with comments)</li>
 * </ul>
 * 
 * <p>This mapper is used for creating detailed post responses that include:</p>
 * <ul>
 *   <li><strong>Post information</strong>: Title, content, excerpt, etc.</li>
 *   <li><strong>Author information</strong>: Author details via AuthorMapper</li>
 *   <li><strong>Comments</strong>: All comments associated with the post via CommentMapper</li>
 * </ul>
 * 
 * <p>The mapper ensures that complex object graphs are properly converted while maintaining
 * clean separation between different types of responses (basic vs. detailed).</p>
 * 
 * @author Tutorial Application
 * @version 1.0
 * @since Spring Boot 3.x
 * @see PostMapper
 * @see PostDetailResponse
 * @see AuthorMapper
 * @see CommentMapper
 */
@Mapper(componentModel = "spring", uses = {AuthorMapper.class, CommentMapper.class})
public interface PostDetailMapper extends PostMapper {

    /**
     * Converts a Post entity to a PostDetailResponse DTO with comments included.
     * 
     * <p>This method demonstrates MapStruct method overriding and complex nested mapping:</p>
     * <ul>
     *   <li><strong>@Override</strong>: Overrides the toDto method from PostMapper to provide
     *       specialized behavior for detailed responses.</li>
     *   <li><strong>@Mapping(target = "comments", source = "comments")</strong>: Explicitly maps the comments
     *       relationship from the Post entity to the PostDetailResponse. This is necessary because
     *       the base PostMapper doesn't handle comments.</li>
     *   <li><strong>Inherited mapping</strong>: Inherits all the basic post and author mapping from PostMapper.</li>
     *   <li><strong>Nested mapping</strong>: Uses CommentMapper to convert each Comment entity to a CommentResponse DTO.</li>
     * </ul>
     * 
     * <p>This method is used when returning detailed post information that includes:</p>
     * <ul>
     *   <li><strong>Post details</strong>: Title, content, excerpt, ID</li>
     *   <li><strong>Author information</strong>: Author details (via AuthorMapper)</li>
     *   <li><strong>Comments</strong>: All comments with their details (via CommentMapper)</li>
     * </ul>
     * 
     * <p>The resulting PostDetailResponse provides a comprehensive view of the post
     * suitable for detailed post views or API responses that need complete information.</p>
     * 
     * @param post The Post entity to convert to a detailed response
     * @return A PostDetailResponse DTO with post, author, and comment information
     */
    @Mapping(target = "comments", source = "comments")
    @Override
    PostDetailResponse toDto(Post post);
}
