package com.cupcake.learn.be.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.messaging.Message
import java.util.function.Consumer

@Configuration
class ConsumerFunctionConfig {

    @Bean
    fun processNotification(): Consumer<Message<String>>? {
        return Consumer<Message<String>> { message: Message<String> ->
            println("message = $message")
        }
    }

}