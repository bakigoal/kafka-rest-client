package com.bakigoal.kafkarestclient.kafka.consumer

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.stereotype.Component

@Component
class MessageListener {

    private val logger: Logger = LoggerFactory.getLogger(MessageListener::class.java)

    @KafkaListener(topics = ["test-topic"])
    fun testTopicListener(@Payload message: String) {
        logger.info("Message in test-topic received: $message")
    }


    @KafkaListener(topics = ["products"])
    fun productsListener(@Payload message: String) {
        logger.info("Message in products received: $message")
    }
}