package com.diamond.diamond.controllers;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.diamond.diamond.controllers.user.SampleMessage;

@Controller
public class WebSocketController {
    // TODO: integrate websockets for token transfers
    /*
     * Prepares a message and returns the estimated gas fee associated with the message
     */
    @MessageMapping("/transfer")
    @SendTo("/topic/transfers")
    public String prepareTransferMessage(SampleMessage message) {
        //TODO: process POST request
        
        return "Transfer Response";
    }

    @MessageMapping("/txn")
    @SendTo("/topic/txns")
    public String initiateTxn(SampleMessage message) {
        return "Txn Response";
    }
}