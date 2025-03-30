package com.diamond.diamond.messaging;

import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.diamond.diamond.configs.KafkaConfig;
import com.diamond.diamond.dtos.account.RegisterUserDto;
import com.diamond.diamond.dtos.payouts.NewPayoutDto;
import com.diamond.diamond.entities.user.Account;
import com.diamond.diamond.entities.user.AccountWallet;
import com.diamond.diamond.services.payments.PayoutService;
import com.diamond.diamond.services.user.AccountService;
import com.diamond.diamond.services.user.AccountWalletService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class KafkaConsumerService {

    private final AccountService accountService;
    private final PayoutService payoutService;
    private final AccountWalletService accountWalletService;

    public KafkaConsumerService(
            AccountService accountService, 
            PayoutService payoutService,
            AccountWalletService accountWalletService) {
        this.accountService = accountService;
        this.payoutService = payoutService;
        this.accountWalletService = accountWalletService;
    }

    /*
     * General consumer service method for handling any given incoming request
     */
    // @KafkaListener(topics = "topic1, topic2", groupId=...
    @KafkaListener(topics = KafkaConfig.TOPIC_NAME, groupId = "${spring.kafka.consumer.group-id}")
    public void consumeMessage(KafkaMessage<?> message) {
        log.info("Received message of type: {}", message.getType());
        
        switch (message.getType()) {
            case USER_REGISTRATION -> handleUserRegistration((RegisterUserDto) message.getPayload());
            case PAYOUT_REQUEST -> handlePayoutRequest((NewPayoutDto) message.getPayload());
            default -> log.warn("Unknown message type: {}", message.getType());
        }
    }

    /*
     * Helper method that processes a registration request
     */
    @KafkaHandler
    private void handleUserRegistration(RegisterUserDto userDto) {
        log.info("Processing user registration: {}", userDto);
        try {
            // Process user registration
            accountService.signUp(userDto);
            log.info("Successfully processed user registration for: {}", userDto.getEmail());
        } catch (Exception e) {
            log.error("Failed to process user registration: {}", e.getMessage());
            // TODO: Implement retry mechanism or dead letter queue
        }
    }

    /*
     * Helper method that processes a payout request
     */
    @KafkaHandler
    private void handlePayoutRequest(NewPayoutDto payoutDto) {
        log.info("Processing payout request: {}", payoutDto);
        try {
            // Get required entities
            Account account = accountService.findAccountById(payoutDto.getAccountId());
            AccountWallet wallet = accountWalletService.findWalletById(payoutDto.getWalletId());
            
            // Process payout request
            payoutService.createPayout(payoutDto, account, wallet);
            log.info("Successfully processed payout request");
        } catch (Exception e) {
            log.error("Failed to process payout request: {}", e.getMessage());
            // TODO: Implement retry mechanism or dead letter queue
        }
    }

} 