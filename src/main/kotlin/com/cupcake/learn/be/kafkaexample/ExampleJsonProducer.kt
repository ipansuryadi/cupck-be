package com.cupcake.learn.be.kafkaexample

import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component

@Component
class ExampleStringProducer(
    private val kafkaTemplate: KafkaTemplate<String, String>
) {

    fun sendStringMessage(message: String) {
        kafkaTemplate.send(EXAMPLE_TOPIC_NAME, message)
    }

}