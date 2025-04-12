package com.diamond.diamond.services.onchain.evm;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Arrays;

import org.bouncycastle.asn1.sec.SECNamedCurves;
import org.bouncycastle.crypto.digests.KeccakDigest;
import org.bouncycastle.crypto.params.ECDomainParameters;
import org.bouncycastle.math.ec.ECPoint;
import org.bouncycastle.util.encoders.Hex;
import org.springframework.stereotype.Service;

import com.diamond.diamond.types.WalletKeypair;

/*
 * Generates an EVM wallet keypair.
 */
@Service
public class EVMWalletCreator {
    private static ECDomainParameters secp256k1;
    
    public EVMWalletCreator() {
        secp256k1 = getSecp256k1Params();
    }

    public static WalletKeypair generate() {
        // Generate private key (32 bytes)
        BigInteger privateKey = new BigInteger(256, new SecureRandom());
        
        // Derive public key
        ECPoint publicKey = secp256k1.getG().multiply(privateKey);
        
        // Compute Ethereum address
        String address = deriveAddress(publicKey);
        
        System.out.println("Private Key: 0x" + privateKey.toString(16));
        System.out.println("Address: " + address);
        return new WalletKeypair(address, "0x" + privateKey.toString(16));
    }

    private static String deriveAddress(ECPoint publicKey) {
        byte[] x = publicKey.getXCoord().getEncoded();
        byte[] y = publicKey.getYCoord().getEncoded();
        byte[] publicKeyBytes = new byte[x.length + y.length];
        System.arraycopy(x, 0, publicKeyBytes, 0, x.length);
        System.arraycopy(y, 0, publicKeyBytes, x.length, y.length);

        KeccakDigest keccak = new KeccakDigest(256);
        keccak.update(publicKeyBytes, 0, publicKeyBytes.length);
        byte[] hash = new byte[32];
        keccak.doFinal(hash, 0);

        byte[] addressBytes = Arrays.copyOfRange(hash, 12, 32);
        return "0x" + Hex.toHexString(addressBytes);
    }

    private static ECDomainParameters getSecp256k1Params() {
        var curve = SECNamedCurves.getByName("secp256k1");
        return new ECDomainParameters(
            curve.getCurve(), curve.getG(), curve.getN(), curve.getH()
        );
    }
}

