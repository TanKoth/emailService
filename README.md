# Email Service

This project is a Spring Boot application that listens to Kafka messages and sends emails using JavaMail.

## Prerequisites

- Java 11 or higher
- Maven 3.6.0 or higher
- Kafka
- A Gmail account with an application-specific password

## Setup

### Kafka

1. **Start Zookeeper**:
    ```sh
    bin/zookeeper-server-start.sh config/zookeeper.properties
    ```

2. **Start Kafka Broker**:
    ```sh
    bin/kafka-server-start.sh config/server.properties
    ```

### Gmail Application-Specific Password

1. Go to your Google Account.
2. Select `Security`.
3. Under `Signing in to Google`, select `App Passwords`. You might need to sign in.
4. At the bottom, choose `Select app` and choose the app you’re using.
5. Choose `Select device` and choose the device you’re using.
6. Select `Generate`.
7. Follow the instructions to enter the App Password. The App Password is the 16-character code in the yellow bar on your device.

## Configuration

Update the `application.properties` file with your Kafka and email configurations:

```properties
spring.kafka.bootstrap-servers=localhost:9092
