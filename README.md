# Spring Boot Tutorial Application

A comprehensive Spring Boot tutorial application demonstrating REST API development, JPA relationships, and modern Spring Boot best practices. This application serves as a learning resource for understanding Spring Boot concepts through a practical blog system with authors, posts, and comments.

## 🚀 Features

### Core Functionality
- **Author Management**: Create and retrieve authors with full name and email
- **Post Management**: Create and retrieve blog posts with title, content, and excerpts
- **Comment System**: Add comments to posts with nested resource patterns
- **RESTful API**: Complete REST API with proper HTTP methods and status codes
- **Database Integration**: JPA/Hibernate with automatic schema generation

### Technical Features
- **Layered Architecture**: Controllers, Services, Repositories, and Entities
- **Data Transfer Objects (DTOs)**: Clean separation between API and domain layers
- **Exception Handling**: Global exception handling with proper HTTP status codes
- **Object Mapping**: MapStruct for type-safe object transformations
- **Performance Optimization**: @EntityGraph for N+1 query prevention
- **Dependency Injection**: Spring's IoC container with @Autowired and @Qualifier

## 🏗️ Architecture

### Technology Stack
- **Spring Boot 3.x**: Main framework
- **Spring Data JPA**: Data access layer
- **H2 Database**: In-memory database for development
- **MapStruct**: Object mapping library
- **Thymeleaf**: Template engine for web views
- **Maven**: Build tool and dependency management

### Project Structure
```
src/main/java/com/example/
├── PostApplication.java              # Main application class
├── AppController.java               # Web controller for main page
├── GlobalExceptionHandler.java      # Global exception handling
├── NotFoundEntityException.java     # Base exception class
├── author/                         # Author domain
│   ├── Author.java                 # JPA entity
│   ├── AuthorRepository.java       # Spring Data repository
│   ├── AuthorService.java          # Business logic
│   ├── AuthorController.java       # REST controller
│   ├── AuthorMapper.java           # Object mapper
│   ├── CreateAuthorRequest.java    # Input DTO
│   ├── AuthorResponse.java         # Output DTO
│   └── AuthorNotFoundException.java # Specific exception
├── post/                          # Post domain
│   ├── Post.java                  # JPA entity with relationships
│   ├── PostRepository.java        # Repository with @EntityGraph
│   ├── PostService.java           # Service with cross-service collaboration
│   ├── PostController.java        # REST controller
│   ├── PostMapper.java            # MapStruct mapper
│   ├── PostDetailMapper.java      # Specialized mapper
│   ├── CreatePostRequest.java     # Input DTO with relationships
│   ├── PostResponse.java          # Basic output DTO
│   ├── PostDetailResponse.java    # Detailed output DTO
│   └── PostNotFoundException.java # Specific exception
└── post/comment/                  # Comment domain
    ├── Comment.java               # JPA entity with lazy loading
    ├── CommentRepository.java     # Repository
    ├── CommentService.java        # Service
    ├── CommentController.java     # REST controller (nested resources)
    ├── CommentMapper.java         # Object mapper
    ├── CreateCommentRequest.java  # Input DTO
    └── CommentResponse.java       # Output DTO
```

## 🛠️ Prerequisites

- **Java 17 or higher**
- **Maven 3.6 or higher**
- **IDE** (IntelliJ IDEA, Eclipse, or VS Code recommended)

## 📦 Installation & Setup

### 1. Clone the Repository
```bash
git clone <repository-url>
cd springboot-tutorial
```

### 2. Build the Project
```bash
mvn clean install
```

### 3. Run the Application
```bash
java -jar target/post-0.0.1-SNAPSHOT.jar
```

The application will start on `http://localhost:8080`

## 🌐 API Endpoints

### Authors
- `GET /api/authors` - Get all authors
- `GET /api/authors/{id}` - Get author by ID
- `POST /api/authors` - Create a new author

### Posts
- `GET /api/posts` - Get all posts (basic info)
- `GET /api/posts/{id}` - Get post by ID (with comments)
- `POST /api/posts` - Create a new post

### Comments
- `POST /api/posts/{postId}/comments` - Create a comment for a post

## 📝 API Examples

### Create an Author
```bash
curl -X POST http://localhost:8080/api/authors \
  -H "Content-Type: application/json" \
  -d '{
    "firstName": "John",
    "lastName": "Doe",
    "email": "john.doe@example.com"
  }'
```

### Create a Post
```bash
curl -X POST http://localhost:8080/api/posts \
  -H "Content-Type: application/json" \
  -d '{
    "title": "My First Blog Post",
    "content": "This is the content of my first blog post...",
    "excerpt": "A brief summary of the post content",
    "authorId": 1
  }'
```

### Create a Comment
```bash
curl -X POST http://localhost:8080/api/posts/1/comments \
  -H "Content-Type: application/json" \
  -d '{
    "text": "This is a great post! Thanks for sharing."
  }'
```

### Get Post with Comments
```bash
curl http://localhost:8080/api/posts/1
```

## 🎯 Key Spring Boot Concepts Demonstrated

### 1. **@SpringBootApplication**
- Combines @Configuration, @EnableAutoConfiguration, and @ComponentScan
- Entry point for the Spring Boot application

### 2. **JPA Annotations**
- `@Entity`: Marks classes as JPA entities
- `@Id` & `@GeneratedValue`: Primary key configuration
- `@OneToMany` & `@ManyToOne`: Relationship mapping
- `@JoinColumn`: Foreign key specification

### 3. **Spring Data JPA**
- `JpaRepository`: Automatic CRUD operations
- `@EntityGraph`: Performance optimization
- Query method generation

### 4. **REST Controllers**
- `@RestController`: JSON response handling
- `@RequestMapping`: URL mapping
- `@GetMapping` & `@PostMapping`: HTTP method mapping
- `@PathVariable` & `@RequestBody`: Parameter binding

### 5. **Dependency Injection**
- `@Autowired`: Automatic dependency injection
- `@Qualifier`: Bean qualification
- `@Service`, `@Repository`, `@Controller`: Component stereotypes

### 6. **Exception Handling**
- `@ControllerAdvice`: Global exception handling
- `@ExceptionHandler`: Specific exception handling
- Custom exception hierarchy

### 7. **Object Mapping**
- MapStruct for type-safe object transformations
- `@Mapper` and `@Mapping` annotations
- Multi-parameter mapping

## 🗄️ Database

The application uses H2 in-memory database by default. The database schema is automatically generated from JPA entities:

### Tables
- **author**: Authors with id, first_name, last_name, email
- **post**: Posts with id, title, content, excerpt, author_id (FK)
- **comment**: Comments with id, text, post_id (FK)

### Relationships
- **Author → Post**: One-to-Many (one author can have many posts)
- **Post → Comment**: One-to-Many (one post can have many comments)

## 🔧 Configuration

### Application Properties
The application uses `src/main/resources/application.properties` for configuration:

```properties
# Database Configuration
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=

## 📚 Learning Objectives

This tutorial application demonstrates:

1. **Spring Boot Fundamentals**
   - Auto-configuration
   - Component scanning
   - Dependency injection

2. **JPA and Database Integration**
   - Entity mapping
   - Relationship management
   - Repository pattern

3. **REST API Development**
   - RESTful design principles
   - HTTP method mapping
   - Request/response handling

4. **Architecture Patterns**
   - Layered architecture
   - DTO pattern
   - Service layer pattern

5. **Exception Handling**
   - Global exception handling
   - Custom exceptions
   - HTTP status code mapping

6. **Performance Optimization**
   - Lazy loading
   - Eager loading with @EntityGraph
   - N+1 query prevention

