// package com.diamond.diamond.messaging;

// import java.util.concurrent.CountDownLatch;

// import org.springframework.stereotype.Component;

// /*
//  * Defines a method for receiving messages
//  */
// @Component
// public class Receiver {
//     // Signals that the message has been received (TODO: not to be used in prod)
//     private CountDownLatch latch = new CountDownLatch(1);

//     public void receiveMessage(String message) {
//         System.out.println("Received < " + message + " >");
//         latch.countDown();
//     }

//     public CountDownLatch getLatch() {
//         return latch;
//     }
// }
