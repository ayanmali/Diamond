package com.diamond.diamond.configs;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

import com.diamond.diamond.dtos.account.RegisterUserDto;
import com.diamond.diamond.dtos.payouts.NewPayoutDto;

@Configuration
@EnableKafka
public class KafkaConfig {
    public static final String TOPIC_NAME = "diamondpay-topic";
    private final String SERVICE_TYPE_MAPPINGS = String.format(
        "userRegistration:%s, payoutRequest:%s",
        RegisterUserDto.class.getName(),
        NewPayoutDto.class.getName()
    );

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;
    
    @Value("${spring.kafka.consumer.group-id}")
    private String groupId;

    @Bean
    public KafkaAdmin kafkaAdmin() {
        Map<String, Object> configs = new HashMap<>();
        configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        return new KafkaAdmin(configs);
    }

    /*
     * Creates a new topic w/ 3 partitions, replication factor of 1
     */
    @Bean
    public NewTopic topic() {
        return new NewTopic(TOPIC_NAME, 3, (short) 1);
    }

    /* Producer Config */

    /*
     * Producer factory for creating Kafka Producer instances
     */
    @Bean
    public ProducerFactory<String, Object> producerFactory() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        
        // Configure type mappings for serialization (when adding messages to the queue)
        configProps.put(JsonSerializer.TYPE_MAPPINGS, SERVICE_TYPE_MAPPINGS);
        return new DefaultKafkaProducerFactory<>(configProps);
    }

    /*
     * Creates the Kafka Template for publishing messages
     */
    @Bean
    public KafkaTemplate<String, Object> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }

    /*
     * Creates Kafka Consumer instances
     */
    @Bean
    public ConsumerFactory<String, Object> consumerFactory() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        configProps.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        configProps.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        configProps.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        
        // Configure type mappings for deserialization (when reading messages from the queue)
        configProps.put(JsonDeserializer.TYPE_MAPPINGS, SERVICE_TYPE_MAPPINGS);
        
        return new DefaultKafkaConsumerFactory<>(configProps);
    }

    /*
     * Allows consumers to listen for messages
     */
    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Object> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, Object> factory = 
            new ConcurrentKafkaListenerContainerFactory<>();
            
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }

    /*
     * Configure listeners to consume sepcific message content via a filter
     * To use this, configure a listener to use this container factory
     */
    // @Bean
    // public ConcurrentKafkaListenerContainerFactory<String, Object>
    // filterKafkaListenerContainerFactory() {

    //     ConcurrentKafkaListenerContainerFactory<String, Object> factory =
    //     new ConcurrentKafkaListenerContainerFactory<>();

    //     factory.setConsumerFactory(consumerFactory());

    //     factory.setRecordFilterStrategy(
    //     record -> record.value().toString().contains("World"));
    //     return factory;
    // }
} 