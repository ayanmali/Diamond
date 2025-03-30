package com.diamond.diamond.messaging;

import java.util.concurrent.CompletableFuture;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import com.diamond.diamond.configs.KafkaConfig;
import com.diamond.diamond.dtos.account.RegisterUserDto;
import com.diamond.diamond.dtos.payouts.NewPayoutDto;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class KafkaProducerService {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public KafkaProducerService(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    /*
     * Service method for pushing a registration request message to the queue
     */
    public void sendUserRegistration(RegisterUserDto userDto) {
        KafkaMessage<RegisterUserDto> message = new KafkaMessage<>(
            MessageType.USER_REGISTRATION,
            userDto
        );
        sendMessage(message);
    }

    /*
     * Service method for pushing a payout request to the queue
     */
    public void sendPayoutRequest(NewPayoutDto payoutDto) {
        KafkaMessage<NewPayoutDto> message = new KafkaMessage<>(
            MessageType.PAYOUT_REQUEST,
            payoutDto
        );
        sendMessage(message);
    }

    private void sendMessage(KafkaMessage<?> message) {
        CompletableFuture<SendResult<String, Object>> future = kafkaTemplate.send(KafkaConfig.TOPIC_NAME, message);
        
        future.whenComplete((result, ex) -> {
            if (ex == null) {
                log.info("Message sent successfully to topic: {} with offset: {}", 
                KafkaConfig.TOPIC_NAME, result.getRecordMetadata().offset());
            } else {
                log.error("Failed to send message to topic: {} with error: {}", 
                    KafkaConfig.TOPIC_NAME, ex.getMessage());
            }
        });
    }
} 