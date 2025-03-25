package com.diamond.diamond.utils;

import java.security.SecureRandom;

import org.bitcoinj.core.Base58;
import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import org.bouncycastle.crypto.generators.Ed25519KeyPairGenerator;
import org.bouncycastle.crypto.params.Ed25519KeyGenerationParameters;
import org.bouncycastle.crypto.params.Ed25519PrivateKeyParameters;
import org.bouncycastle.crypto.params.Ed25519PublicKeyParameters;
import org.springframework.stereotype.Component;

import com.diamond.diamond.types.WalletKeypair;

@Component
public class KeypairCreator {
    Ed25519KeyPairGenerator keyPairGenerator;

    public KeypairCreator(Ed25519KeyPairGenerator keyPairGenerator) {
        this.keyPairGenerator = keyPairGenerator;
    }

    public WalletKeypair generate() {
        // 1. Generate keypair
        keyPairGenerator.init(new Ed25519KeyGenerationParameters(new SecureRandom()));
        AsymmetricCipherKeyPair keyPair = keyPairGenerator.generateKeyPair();

        // 2. Extract keys
        Ed25519PrivateKeyParameters privateKey = (Ed25519PrivateKeyParameters) keyPair.getPrivate();
        Ed25519PublicKeyParameters publicKey = (Ed25519PublicKeyParameters) keyPair.getPublic();
        
        byte[] privateKeyBytes = privateKey.getEncoded();
        byte[] publicKeyBytes = publicKey.getEncoded();
        
        // 3. Create 64-byte secret key (private + public)
        byte[] secretKey = new byte[64];
        System.arraycopy(privateKeyBytes, 0, secretKey, 0, 32);
        System.arraycopy(publicKeyBytes, 0, secretKey, 32, 32);

        // 4. Convert public key to Base58 address
        String base58Address = Base58.encode(publicKeyBytes);

        return new WalletKeypair(base58Address, bytesToHex(privateKeyBytes));
        
        // System.out.println("Public Address: " + base58Address);
        // System.out.println("Secret Key: " + bytesToHex(secretKey));
    }
    
    /*
     * Helper method to convert the private key into a String
     */
    private static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
}

