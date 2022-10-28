package com.bakigoal.kafkarestclient.kafka.producer

import org.apache.kafka.clients.producer.ProducerRecord
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component


@Component
class MessageSender(
    private val kafkaTemplate: KafkaTemplate<String, String>
) {

    fun send(topic: String, message: String) {
        kafkaTemplate.send(ProducerRecord(topic, message))
    }
}