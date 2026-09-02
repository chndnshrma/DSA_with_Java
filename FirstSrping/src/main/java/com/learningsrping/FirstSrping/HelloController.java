package com.learningsrping.FirstSrping;

import org.springframework.web.bind.annotation.*;

@RestController
public class HelloController {

    @GetMapping("/hello/{name}")
    public HelloResponse getName(@PathVariable String name) {
        return new HelloResponse("Hello, " + name);
    }

    @GetMapping("/hello")
    public HelloResponse print() {
        return new HelloResponse("Hello World!");
    }

    @PostMapping("/hello")
    public HelloResponse helloPost(@RequestBody String name) {
        return new HelloResponse("hello" + name + "!");
    }
}
