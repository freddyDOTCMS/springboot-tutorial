package com.example;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Main application controller that handles web page requests for the tutorial application.
 * 
 * <p>This controller demonstrates basic Spring MVC concepts:</p>
 * <ul>
 *   <li><strong>@Controller</strong>: This annotation indicates that this class serves as a web controller,
 *       capable of handling HTTP requests. It's a specialized form of @Component that is designed
 *       to handle web requests. Spring will automatically detect this class and register it as a bean.</li>
 *   <li><strong>@GetMapping</strong>: This annotation maps HTTP GET requests to specific handler methods.
 *       It's a composed annotation that acts as a shortcut for @RequestMapping(method = RequestMethod.GET).</li>
 * </ul>
 * 
 * <p>This controller works with Thymeleaf templates (located in src/main/resources/templates/)
 * to render HTML pages. When a method returns a String, Spring interprets it as a view name
 * and looks for a corresponding template file.</p>
 * 
 * <p>Key concepts demonstrated:</p>
 * <ul>
 *   <li>Web controller setup with @Controller</li>
 *   <li>HTTP GET request mapping with @GetMapping</li>
 *   <li>Template-based view resolution</li>
 *   <li>Integration with Thymeleaf templating engine</li>
 * </ul>
 * 
 * @author Tutorial Application
 * @version 1.0
 * @since Spring Boot 3.x
 */
@Controller
public class AppController {
    
    /**
     * Handles requests to the root URL ("/") and returns the main application page.
     * 
     * <p>This method demonstrates:</p>
     * <ul>
     *   <li><strong>@GetMapping("/")</strong>: Maps HTTP GET requests to the root URL to this method</li>
     *   <li><strong>Return value</strong>: Returns "app" which tells Spring to look for templates/app.html</li>
     * </ul>
     * 
     * <p>When a user visits http://localhost:8080/, this method will be called and
     * the app.html template will be rendered and returned to the browser.</p>
     * 
     * @return The view name "app" which corresponds to templates/app.html
     */
    @GetMapping("/")
    public String index() {
        return "app"; // Loads templates/app.html
    }
}