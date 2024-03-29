package io.pivotal.pal.tracker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {

    private String hello;

    @GetMapping("/")
    public String sayHello(){
        return hello;
    }

    @Autowired
    public WelcomeController(@Value("${welcome.message}") String hello){
        this.hello = hello;

    }
}
