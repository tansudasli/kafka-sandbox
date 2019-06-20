# kafka-basics - Java APIs
Basic real consumer and producer scenarios for **e-commerce product additions** from a merchant-app.


## How to Start


9. on GCP, Create kafka topic w/ `kafka-topics.sh --bootstrap-server localhost:9092 --topic recently-added-products --replication-factor 1 --partitions 2 --create`
10. on GCP, check topic is created w/ `kafka-topics.sh --bootstrap-server localhost:9092 --list`
11. on GCP, listen topic w/ `kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic recently-added-products --from-beginning`
12. Run app 
    - [ ] on Local,
        - Open `9092` port on the Kafka Instance on GCP.
        - Change IP in Producer.java file.
    - [x] on GCP instance
        - `git clone https://github.com/tansudasli/kafka-sandbox.git`
        - `cd ~/kafka-sandbox/kafka-basics`
        - to start Consumer app use `./gradlew runApp -PmainClass=consumer`
        - to start Producer app use `./gradlew runApp -PmainClass=producer`
