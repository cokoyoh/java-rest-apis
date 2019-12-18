package com.springbootrestful.restfulapis.home;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    @GetMapping("/")
    public String welcome() {
        return "Welcome to SpringBoot";
    }

    @GetMapping(path = "/hello-world")
    public String helloWorld() {
        return "Hello World!";
    }

    @GetMapping(path = "hello-world-bean")
    public HelloWorldBean helloWorldBean() {
        return new HelloWorldBean("Some string bean");
    }
}
