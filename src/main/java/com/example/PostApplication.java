package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main Spring Boot application class that serves as the entry point for the tutorial application.
 * 
 * <p>This class demonstrates the fundamental Spring Boot setup:</p>
 * <ul>
 *   <li><strong>@SpringBootApplication</strong>: This is a convenience annotation that adds all of the following:
 *     <ul>
 *       <li><strong>@Configuration</strong>: Tags the class as a source of bean definitions for the application context</li>
 *       <li><strong>@EnableAutoConfiguration</strong>: Tells Spring Boot to start adding beans based on classpath settings, 
 *           other beans, and various property settings</li>
 *       <li><strong>@ComponentScan</strong>: Tells Spring to look for other components, configurations, and services 
 *           in the com/example package, allowing it to find the controllers</li>
 *     </ul>
 *   </li>
 * </ul>
 * 
 * <p>The main method uses SpringApplication.run() to launch the application. This method:</p>
 * <ul>
 *   <li>Creates the ApplicationContext</li>
 *   <li>Registers a CommandLinePropertySource to expose command line arguments as Spring properties</li>
 *   <li>Refreshes the application context, loading all singleton beans</li>
 *   <li>Triggers any CommandLineRunner beans</li>
 * </ul>
 * 
 * <p>This tutorial application demonstrates a RESTful API for managing posts, authors, and comments
 * with proper Spring Boot conventions and best practices.</p>
 * 
 * @author Tutorial Application
 * @version 1.0
 * @since Spring Boot 3.x
 */
@SpringBootApplication
public class PostApplication {

    /**
     * Main method that serves as the entry point for the Spring Boot application.
     * 
     * <p>This method initializes the Spring application context and starts the embedded web server.
     * The application will be accessible at http://localhost:8080 by default.</p>
     * 
     * @param args Command line arguments passed to the application
     */
    public static void main(String[] args) {
        SpringApplication.run(PostApplication.class, args);
    }

}
