package com.bakigoal.kafkarestclient.controller

import com.bakigoal.kafkarestclient.kafka.producer.MessageSender
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/kafka/topic")
class MessageToKafkaSenderController(
    private val messageSender: MessageSender
) {

    val log: Logger = LoggerFactory.getLogger(MessageToKafkaSenderController::class.java)

    @PostMapping("/{topic}")
    fun send(
        @PathVariable topic: String,
        @RequestBody message: String,
        @RequestParam(required = false, name = "count") count: Int?,
    ): String {
        log.info("Count: $count")

        repeat(count?:1) {
            messageSender.send(topic, message)
        }
        return "Messages($count) to topic $topic is sent: \n$message"
    }
}