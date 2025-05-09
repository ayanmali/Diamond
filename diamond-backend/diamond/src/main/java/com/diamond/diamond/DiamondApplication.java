package com.diamond.diamond;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.diamond.diamond.services.RedisService;



@RestController
@SpringBootApplication
@EnableScheduling
public class DiamondApplication {

    @Autowired
    private RedisService redisService;

    @GetMapping("/")
    public String base() {
        return "Welcome to Diamond";
    }
    
    // @GetMapping("/home")
    // public Map<String, String> home(@AuthenticationPrincipal OAuth2User principal) {
    //     return Map.of("name", principal.getAttribute("name"), "email", principal.getAttribute("email"));
    // }

    
    @GetMapping("/error")
    public String error() {
        return "Error";
    }

    @GetMapping("/redis")
    public Map<String, Object> writeRedis() {
        System.out.println("Writing to Redis DB");
        redisService.set("app", "diamondpay");
        return Map.of("app", "diamondpay");
    }

    @GetMapping("/redis-get")
    public Object getRedis() {
        return redisService.get("app");
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

    public static void main(String[] args) throws Exception {
        //System.out.println(ProcessExecutor.executeScript());
        SpringApplication.run(DiamondApplication.class, args);

        // Encode the private key into a string
        // byte[] rawData = AccountService.generateKey().getEncoded();
        // String encodedKey = Base64.getEncoder().encodeToString(rawData);
        // System.out.println("ENCODED ENCRYPTION KEY: " + encodedKey);
        // Convert the string back into a SecretKey
        //byte[] decodedKey = Base64.getDecoder().decode(encodedKey);
        //SecretKey originalKey = new SecretKeySpec(decodedKey, 0, decodedKey.length, "AES");
        
        // CircleGrpcClient client = new CircleGrpcClient("localhost", 50051);
        // try {
        //     client.createWalletSet("test_wallet");
        // } finally {
        //     client.shutdown();
        // }
    }

}
