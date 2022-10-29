example of docker-compose.yaml

```yaml
version: '3.8'
  services:
    kafka-rest-client:
      build: .
      ports:
        - "8092:8092"
      environment:
        SPRING_KAFKA_CONSUMER_BOOTSTRAP_SERVERS: localhost:9092
        SPRING_KAFKA_PRODUCER_BOOTSTRAP_SERVERS: localhost:9092
```
