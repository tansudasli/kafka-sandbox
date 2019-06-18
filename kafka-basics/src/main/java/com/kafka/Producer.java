package com.kafka;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Producer {

    private static Logger logger = LoggerFactory.getLogger(Producer.class);

    private static InputStream inputStream;

    private static Map<String, String> products = Stream.of(new String[][] {
            {"clothes", "id:100, category:clothes, price:1000"},
            {"watches", "id:100, category:watches, price:500"},
            {"clothes", "id:100, category:clothes, price:100"},
            {"shoes", "id:100, category:shoes, price:250, brand:nike"},
            {"shoes", "id:100, category:shoes, price:1000, brand: adidas"},
            {"shoes", "id:100, category:shoes, price:1000, brand: adidas"},
            {"clothes", "id:100, category:clothes, price:400, brand:gucci"}
    }).collect(Collectors.toMap(data -> data[0], data -> data[1]));

    //    private static log
    public static void main(String[] args) throws IOException {

        Properties properties = new Properties();

        try {
            inputStream = Producer.class.getClassLoader().getResourceAsStream("kafka-server.properties");

            properties.load(inputStream);

            logger.info("kafka-server.properties file loaded");

        } catch (IOException e) {
            e.printStackTrace();

            logger.error("kafka-server.properties file not loaded", e);
        }
        finally {
            if (inputStream != null)
                inputStream.close();
        }

        KafkaProducer<String, String> producer = new KafkaProducer<>(properties);

        products.forEach((k, v) -> {

            ProducerRecord<String, String> record = new ProducerRecord<>(properties.getProperty("topic"), k, v);

            producer.send(record, (metadata, exception) -> {

                if (exception == null)
                logger.info("Received new message: " +
                        "\ntopic: " + metadata.topic() +
                        "\ntimestamp: " + metadata.timestamp() +
                        "\npartition: " + metadata.partition() +
                        "\noffset: " + metadata.offset());
                else
                    logger.error("Received new message:", exception);

            });
        });

        producer.close();
    }
}
