package com.diamond.diamond.configs;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PaymentInitializer {
    @Bean
    static <T> T initializePaymentObject(Class<T> clazz, Map<String, String> attributes) {
        try {
            T instance = clazz.getDeclaredConstructor().newInstance();
            for (Map.Entry<String, String> entry : attributes.entrySet()) {
                Field field = clazz.getDeclaredField(entry.getKey());
                field.setAccessible(true);
                field.set(instance, entry.getValue());
            }
            return instance;
        } catch (IllegalAccessException | IllegalArgumentException | InstantiationException | NoSuchFieldException | NoSuchMethodException | SecurityException | InvocationTargetException e) {
            e.printStackTrace();
            return null; // or handle the exception as needed
        }
    }
}