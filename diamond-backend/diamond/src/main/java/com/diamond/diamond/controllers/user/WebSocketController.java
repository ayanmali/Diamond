package com.diamond.diamond.controllers.user;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class WebSocketController {
    // TODO: integrate websockets for token transfers
    /*
     * Prepares a message and returns the estimated gas fee associated with the message
     */
    @MessageMapping("/message")
    @SendTo("/topic/messages")
    public String prepareTransferMessage(SampleMessage message) {
        //TODO: process POST request
        
        return "WebSocket Response";
    }
}
