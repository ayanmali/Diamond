package com.diamond.diamond.messaging.kafka;
// package com.diamond.diamond.messaging;

// import org.springframework.kafka.annotation.KafkaHandler;
// import org.springframework.kafka.annotation.KafkaListener;
// import org.springframework.stereotype.Service;

// import com.diamond.diamond.configs.KafkaConfig;
// import com.diamond.diamond.dtos.account.RegisterUserDto;
// import com.diamond.diamond.dtos.customer.NewCustomerDto;
// import com.diamond.diamond.dtos.payments.new_payments.NewPaymentDto;
// import com.diamond.diamond.dtos.payments.txns.NewPaymentTxnDto;
// import com.diamond.diamond.dtos.wallets.NewAccountWalletDto;
// import com.diamond.diamond.entities.payments.Payment;
// import com.diamond.diamond.entities.user.Account;
// import com.diamond.diamond.services.payments.PaymentService;
// import com.diamond.diamond.services.payments.PayoutService;
// import com.diamond.diamond.services.user.AccountService;
// import com.diamond.diamond.services.user.AccountWalletService;

// import lombok.extern.slf4j.Slf4j;

// @Slf4j
// @Service
// public class KafkaConsumerService {

//     private final PaymentService paymentService;

//     private final AccountService accountService;
//     private final PayoutService payoutService;
//     private final AccountWalletService accountWalletService;

//     public KafkaConsumerService(
//             AccountService accountService, 
//             PayoutService payoutService,
//             AccountWalletService accountWalletService, PaymentService paymentService) {
//         this.accountService = accountService;
//         this.payoutService = payoutService;
//         this.accountWalletService = accountWalletService;
//         this.paymentService = paymentService;
//     }

//     /*
//      * Defines logic for handling any given incoming request
//      */
//     // @KafkaListener(topics = "topic1, topic2", groupId=...
//     @KafkaListener(topics = KafkaConfig.TOPIC_NAME, groupId = "${spring.kafka.consumer.group-id}")
//     public void consumeMessage(KafkaMessage<?> message) {
//         log.info("Received message of type: {}", message.getType());
        
//         switch (message.getType()) {
//             case USER_REGISTRATION -> handleUserRegistration((RegisterUserDto) message.getPayload());
//             case NEW_WALLET -> handleNewWalletRequest((NewAccountWalletDto) message.getPayload());
//             case NEW_PAYMENT -> handleNewPaymentRequest((NewPaymentDto) message.getPayload());
//             case NEW_CUSTOMER -> handleNewCustomerRequest((NewCustomerDto) message.getPayload());
//             case NEW_TRANSACTION -> handleNewTransactionRequest((NewPaymentTxnDto) message.getPayload());
//             default -> log.warn("Unknown message type: {}", message.getType());
//         }
//     }

//     /*
//      * Helper method that processes a registration request
//      */
//     @KafkaHandler
//     private void handleUserRegistration(RegisterUserDto userDto) {
//         log.info("Processing user registration: {}", userDto);
//         try {
//             // Process user registration
//             accountService.signUp(userDto);
//             log.info("Successfully processed user registration for: {}", userDto.getEmail());
//         } catch (Exception e) {
//             log.error("Failed to process user registration: {}", e.getMessage());
//             // TODO: Implement retry mechanism or dead letter queue
//         }
//     }

//     /*
//      * Helper method that processes a mew wallet request
//      */
//     @KafkaHandler
//     private void handleNewWalletRequest(NewAccountWalletDto walletDto) {
//         log.info("Processing new wallet request: {}", walletDto);
//         try {
//             // // Get required entities
//             Account account = accountService.findAccountById(walletDto.getAccountId());
            
//             // Process new wallet request
//             // payoutService.createPayout(payoutDto, account, wallet);
//             accountWalletService.saveWallet(walletDto, account, address);
//             log.info("Successfully processed payout request");
//         } catch (Exception e) {
//             log.error("Failed to process payout request: {}", e.getMessage());
//             // TODO: Implement retry mechanism or dead letter queue
//         }
//     }

//     /*
//      * Helper method that processes a mew wallet request
//      */
//     @KafkaHandler
//     private void handleNewPaymentRequest(NewPaymentDto paymentDto) {
//         log.info("Processing new wallet request: {}", walletDto);
//         try {
//             // // Get required entities
//             Account account = accountService.findAccountById(walletDto.getAccountId());
            
//             // Process new wallet request
//             // payoutService.createPayout(payoutDto, account, wallet);
            
//             log.info("Successfully processed payout request");
//         } catch (Exception e) {
//             log.error("Failed to process payout request: {}", e.getMessage());
//             // TODO: Implement retry mechanism or dead letter queue
//         }
//     }

    

// } 