package com.diamond.diamond;

import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
@EnableScheduling
public class DiamondApplication {

    @GetMapping("/home")
    public Map<String, String> home(@AuthenticationPrincipal OAuth2User principal) {
        return Map.of("name", principal.getAttribute("name"));
    }

    // @Bean
    // public WebMvcConfigurer corsConfigurer() {
    //     return new WebMvcConfigurer() {
    //         @Override
    //         public void addCorsMappings(CorsRegistry registry) {
    //             registry.addMapping("/greeting-javaconfig").allowedOrigins("http://localhost:9000");
    //         }
    //     };
    // }

    public static void main(String[] args) throws InterruptedException {
        //System.out.println(ProcessExecutor.executeScript());
        SpringApplication.run(DiamondApplication.class, args);

        
        // CircleGrpcClient client = new CircleGrpcClient("localhost", 50051);
        // try {
        //     client.createWalletSet("test_wallet");
        // } finally {
        //     client.shutdown();
        // }
    }

}
