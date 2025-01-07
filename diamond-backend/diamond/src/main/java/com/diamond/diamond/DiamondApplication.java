package com.diamond.diamond;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.RestController;

// @RestController
@SpringBootApplication

public class DiamondApplication {

    // @GetMapping("/home")
    // String home() {
    //     return "Welcome to Diamond";
    // }
    public static void main(String[] args) {
        SpringApplication.run(DiamondApplication.class, args);
        
    }

}
