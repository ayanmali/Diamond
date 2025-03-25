package com.diamond.diamond.configs;

import org.bouncycastle.crypto.generators.Ed25519KeyPairGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KeypairCreatorConfig {

    @Bean
    public Ed25519KeyPairGenerator getGenerator() {
        return new Ed25519KeyPairGenerator();
    }
}
