package com.kafka;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Consumer {
    private static Logger logger = LoggerFactory.getLogger(Producer.class);
    private static String bootstrapServer = "localhost:9092";
    private static String topic = "recently-added-products";
    private static String groupId = "product-analytics-consumer-app";
    private static String offsetReset = "earliest";

    private static Map<String, String> variables = Stream.of(new String[][] {
            {ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServer},
            {ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName()},
            {ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName()},
            {ConsumerConfig.GROUP_ID_CONFIG, groupId},
            {ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, offsetReset},
            {"topic", topic}
    }).collect(Collectors.toMap(data -> data[0], data -> data[1]));

    private static Properties properties = new Properties();

    public static void main(String[] args) {

        properties.putAll(variables);

        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(properties);

        consumer.subscribe(Collections.singletonList(topic));

        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));

            records.forEach((record) -> {
                logger.info(String.format("Key= %s { %s }", record.key(), record.value()));
            });
        }


    }

}
