package com.example.crowdfunding.configs;

//@EnableKafka
//@Configuration
public class KafkaConfig {

//    private final String bootstrapServers = "localhost:9092";

//    @Bean
//    NewTopic createTopic(){
//        return TopicBuilder.name("request-topic")
//                .partitions(1)
//                .replicas(1)
//                .build();
//
//    }

//    @Bean
//    public ProducerFactory<String, RequestTransactionDto> transactionDtoProducerFactory() {
//        Map<String, Object> configProps = new HashMap<>();
//        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
//        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
//        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
//        return new DefaultKafkaProducerFactory<>(configProps);
//    }
//
//    @Bean
//    public KafkaTemplate<String, RequestTransactionDto> transactionDtoKafkaTemplate() {
//        return new KafkaTemplate<>(transactionDtoProducerFactory());
//    }
//
//    @Bean
//    public ConsumerFactory<String, RequestTransactionDto> requestTransactionDtoConsumerFactory() {
//        Map<String, Object> configProps = new HashMap<>();
//        configProps.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
//        configProps.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
//        configProps.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
//        configProps.put(JsonDeserializer.TRUSTED_PACKAGES, "*");
//        return new DefaultKafkaConsumerFactory<>(configProps);
//    }
//
//    @Bean
//    public ConcurrentKafkaListenerContainerFactory<String, RequestTransactionDto> transactionDtoKafkaListenerContainerFactory() {
//        ConcurrentKafkaListenerContainerFactory<String, RequestTransactionDto> factory = new ConcurrentKafkaListenerContainerFactory<>();
//        factory.setConsumerFactory(requestTransactionDtoConsumerFactory());
//        return factory;
//    }
}



