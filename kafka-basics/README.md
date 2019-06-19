# kafka-basics - Java APIs

## How to Start


9. on GCP, Create kafka topic w/ `kafka-topics.sh --bootstrap-server localhost:9092 --topic recently-added-products --replication-factor 1 --partitions 2 --create`
10. on GCP, check topic is created w/ `kafka-topics.sh --bootstrap-server localhost:9092 --list`
11. on GCP, listen topic w/ `kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic recently-added-products --from-beginning`
12. Run app 
    - [x] on Local,
        - Open `9092` port on the Kafka Instance on GCP.
        - Change IP in Producer.java file.
    - [x] on GCP instance
        - `cd ~/kafka-sandbox/kafka-basics`
        - `./gradlew run`
