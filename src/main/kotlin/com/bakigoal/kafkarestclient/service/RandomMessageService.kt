package com.bakigoal.kafkarestclient.service

import com.fasterxml.jackson.databind.ObjectMapper
import org.apache.commons.lang3.RandomStringUtils
import org.apache.commons.lang3.RandomUtils
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.util.*
import kotlin.random.Random

@Service
class RandomMessageService {

    val log: Logger = LoggerFactory.getLogger(RandomMessageService::class.java)

    val objectMapper: ObjectMapper = ObjectMapper()

    fun createMessage(map: Map<String, Any>, fields: List<String>?): String {
        log.info("fields: $fields")
        val result = map.toMutableMap()
        fields?.forEach {
            val keys = it.split(".")
            log.info("keys: $keys")
            val first = keys.first()
            val value = result[first]
            if (value != null) {
                if (value is Map<*, *>) {
                    log.info("Object is found... $value")
                    result[first] = createObject(value as Map<String, Any>, keys, 1)
                } else {
                    result[first] = generateValue(value)
                }
            }
        }
        return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(result)
    }

    private fun createObject(map: Map<String, Any>, keys: List<String>, index: Int): Map<String, Any> {
        if (keys.size <= index) {
            return mapOf()
        }
        val result = map.toMutableMap()
        val key = keys[index]
        val value = map[key]
        if (value != null) {
            if (value is Map<*, *>) {
                log.info("Object is found... $value")
                result[key] = createObject(value as Map<String, Any>, keys, index + 1)
            } else {
                result[key] = generateValue(value)
            }
        }
        return result
    }

    private fun generateValue(value: Any): Any {
        return when (value) {
            is String -> generateFromString(value)
            is Int -> RandomUtils.nextInt(1, 10000000)
            is Boolean -> Random.nextBoolean()
            else -> value
        }
    }

    private fun generateFromString(value: String): String {
        return try {
            UUID.fromString(value)
            UUID.randomUUID().toString();
        } catch (e: IllegalArgumentException) {
            // not UUID format
            RandomStringUtils.randomAlphanumeric(RandomUtils.nextInt(10, 100))
        }
    }
}