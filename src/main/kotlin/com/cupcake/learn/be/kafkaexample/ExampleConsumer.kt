package com.cupcake.learn.be.kafkaexample

import org.slf4j.LoggerFactory
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component

@Component
class ExampleConsumer {
    private val logger = LoggerFactory.getLogger(this.javaClass)

    @KafkaListener(topics = [EXAMPLE_TOPIC_NAME], groupId = GROUP_ID)
    fun firstListener(message: String) {
        logger.info("Message received: [$message]")
    }
}
