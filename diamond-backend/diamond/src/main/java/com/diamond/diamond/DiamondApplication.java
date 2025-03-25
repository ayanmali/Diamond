package com.diamond.diamond;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
@EnableScheduling
public class DiamondApplication {

    @GetMapping("/home")
    public String home() {
        return "Welcome to Diamond";
    }
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
