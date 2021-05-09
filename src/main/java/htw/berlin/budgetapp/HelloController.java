package htw.berlin.budgetapp;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @RequestMapping("/")
    public String helloWorld() {
        return "Hello World! This is my first Spring Boot Application.";
    }


}
