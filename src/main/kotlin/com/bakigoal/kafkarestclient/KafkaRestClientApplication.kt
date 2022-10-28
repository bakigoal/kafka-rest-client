package com.bakigoal.kafkarestclient

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.kafka.annotation.EnableKafka

@EnableKafka
@SpringBootApplication
class KafkaRestClientApplication

fun main(args: Array<String>) {
    runApplication<KafkaRestClientApplication>(*args)
}
