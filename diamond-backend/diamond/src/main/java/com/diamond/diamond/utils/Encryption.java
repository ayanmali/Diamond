// package com.diamond.diamond.utils;

// import java.security.KeyFactory;
// import java.security.PublicKey;
// import java.security.spec.X509EncodedKeySpec;
// import java.util.Base64;

// import javax.crypto.Cipher;

// import static com.Keys.CIRCLE_ENTITY_PUBLIC_KEY;
// import static com.Keys.CIRCLE_ENTITY_SECRET;

// public class Encryption {
//     public static String generateEntityCiphertext() {
//         try {
//             //Dotenv dotenv = Dotenv.load();
//             String entitySecret = CIRCLE_ENTITY_SECRET;
//             System.out.println(entitySecret);
//             String entityPublicKey = CIRCLE_ENTITY_PUBLIC_KEY; // Replace with your actual public key
//             System.out.println(entityPublicKey);

//             // Encrypt the secret
//             String encrypted = encrypt(entitySecret, entityPublicKey);
//             System.out.println(encrypted);
//             return encrypted;
            
//             //return encrypt(entitySecret, entityPublicKey);
//         } catch (Exception e) {
//             System.out.println("Error encrypting entity secret");
//             return null;
//         }
//     }

//     public static String encrypt(String data, String publicKeyStr) throws Exception {
//         // Convert the public key string to a PublicKey object
//         byte[] publicKeyBytes = Base64.getDecoder().decode(publicKeyStr);
//         X509EncodedKeySpec keySpec = new X509EncodedKeySpec(publicKeyBytes);
//         KeyFactory keyFactory = KeyFactory.getInstance("RSA");
//         PublicKey publicKey = keyFactory.generatePublic(keySpec);

//         // Create a Cipher object and initialize it with the public key
//         Cipher cipher = Cipher.getInstance("RSA");
//         cipher.init(Cipher.ENCRYPT_MODE, publicKey);

//         // Encrypt the data
//         byte[] encryptedBytes = cipher.doFinal(data.getBytes("UTF-8"));

//         // Convert the encrypted bytes to a Base64 encoded string
//         return Base64.getEncoder().encodeToString(encryptedBytes);
//     }
// }
