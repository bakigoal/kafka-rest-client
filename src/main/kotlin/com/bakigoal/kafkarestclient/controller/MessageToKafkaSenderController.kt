package com.bakigoal.kafkarestclient.controller

import com.bakigoal.kafkarestclient.kafka.producer.MessageSender
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/kafka")
class MessageToKafkaSenderController(
    private val messageSender: MessageSender
) {

    @PostMapping("/{topic}/send")
    fun send(@PathVariable topic: String, @RequestBody message: String): String {
        messageSender.send(topic, message)
        return "Message to topic $topic is sent: \n$message"
    }
}