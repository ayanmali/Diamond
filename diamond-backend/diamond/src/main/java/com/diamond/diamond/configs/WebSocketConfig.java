package com.diamond.diamond.configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
    
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        // Configure RabbitMQ as the message broker
        registry.enableStompBrokerRelay("/topic", "/queue")
                .setRelayHost("rabbitmq")    // RabbitMQ host
                .setRelayPort(61613)          // STOMP port (default is 61613)
                .setClientLogin("guest")      // Default RabbitMQ credentials
                .setClientPasscode("guest")
                .setSystemLogin("guest")
                .setSystemPasscode("guest");

        // Set prefix for controller methods
        registry.setApplicationDestinationPrefixes("/app");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws")
                .setAllowedOrigins("*");
                //.withSockJS();
    }
}
