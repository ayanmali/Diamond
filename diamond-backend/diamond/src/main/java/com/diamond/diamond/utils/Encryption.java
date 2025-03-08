package com.diamond.diamond.utils;

import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

import javax.crypto.Cipher;

import io.github.cdimascio.dotenv.Dotenv;

public class Encryption {
    public static String generateEntityCiphertext() {
        try {
            Dotenv dotenv = Dotenv.configure().load();
            String entitySecret = dotenv.get("CIRCLE_ENTITY_SECRET");
            String entityPublicKey = dotenv.get("CIRCLE_ENTITY_PUBLIC_KEY"); // Replace with your actual public key

            // Encrypt the secret
            return encrypt(entitySecret, entityPublicKey);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String encrypt(String data, String publicKeyStr) throws Exception {
        // Convert the public key string to a PublicKey object
        byte[] publicKeyBytes = Base64.getDecoder().decode(publicKeyStr);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(publicKeyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PublicKey publicKey = keyFactory.generatePublic(keySpec);

        // Create a Cipher object and initialize it with the public key
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);

        // Encrypt the data
        byte[] encryptedBytes = cipher.doFinal(data.getBytes("UTF-8"));

        // Convert the encrypted bytes to a Base64 encoded string
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }
}
