package com.bakigoal.kafkarestclient.controller

import com.bakigoal.kafkarestclient.kafka.producer.MessageSender
import com.bakigoal.kafkarestclient.service.RandomMessageService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/kafka/topic")
class MessageToKafkaSenderController(
    private val messageSender: MessageSender,
    private val randomMessageService: RandomMessageService
) {

    @PostMapping("/{topic}")
    fun send(
        @PathVariable topic: String,
        @RequestBody message: String,
        @RequestParam(required = false, name = "count") count: Int?,
    ): String {
        repeat(count ?: 1) {
            messageSender.send(topic, message)
        }
        return "Messages($count) to topic $topic is sent: \n$message"
    }

    @PostMapping("/{topic}/random")
    fun sendWithRandomValues(
        @PathVariable topic: String,
        @RequestBody body: Map<String, Any>,
        @RequestParam(required = false, name = "count") count: Int?,
        @RequestParam(required = false, name = "fields") fields: List<String>?,
    ): String {
        repeat(count ?: 1) {
            messageSender.send(topic, randomMessageService.createMessage(body, fields))
        }
        return "Messages($count) to topic $topic is sent: \n$body"
    }
}