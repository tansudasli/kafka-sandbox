package com.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.Properties;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Producer {

    private static Logger logger = LoggerFactory.getLogger(Producer.class);

    private static Map<String, String> products = Stream.of(new String[][] {
            {"clothes:100", "id:100, category:clothes, price:1000"},
            {"watches:200", "id:200, category:watches, price:500"},
            {"clothes:300", "id:300, category:clothes, price:100"},
            {"shoes:400", "id:400, category:shoes, price:250, brand:nike"},
            {"shoes:500", "id:500, category:shoes, price:1000, brand: adidas"},
            {"shoes:600", "id:600, category:shoes, price:1000, brand: adidas"},
            {"clothes:700", "id:700, category:clothes, price:400, brand:gucci"}
    }).collect(Collectors.toMap(data -> data[0], data -> data[1]));

    private static Map<String, String> variables = Stream.of(new String[][] {
            {ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "35.246.179.182:9092"},
            {ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName()},
            {ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName()},
            {"topic", "recently-added-products"}
    }).collect(Collectors.toMap(data -> data[0], data -> data[1]));

    private static Properties properties = new Properties();

    public static void main(String[] args) {

        properties.putAll(variables);

        KafkaProducer<String, String> producer = new KafkaProducer<>(properties);

        products.forEach((k, v) -> {

            ProducerRecord<String, String> record = new ProducerRecord<>(properties.getProperty("topic"), k.split(":")[0], v);

            producer.send(record, (metadata, exception) -> {

                if (exception == null)
                    logger.info(String.format("Received new message: on topic %s\nkey= %s {timestamp: %d, partition: %d, offset: %d}",
                            record.value(), metadata.topic(), metadata.timestamp(), metadata.partition(), metadata.offset()));
                else
                    logger.error("Received new message:", exception);
            });
        });

        producer.close();
    }
}
