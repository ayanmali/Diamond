package com.diamond.diamond.controllers.payments;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.diamond.diamond.entities.payments.Payment;
import com.diamond.diamond.services.payments.PaymentService;



@RestController
@RequestMapping("/payments")
public class PaymentController {
    private final PaymentService<Payment> paymentService;
    
    public PaymentController(PaymentService<Payment> paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/new")
    public String postMethodName(@RequestBody String entity) {
        //TODO: process POST request
        
        return entity;
    }

    @GetMapping("/id")
    public String getMethodName(@RequestParam String id) {
        return new String();
    }
    
    

}
