package com.cupcake.learn.be.kafkaexample

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
class ExampleController(
    private val exampleStringProducer: ExampleStringProducer
) {

    @PostMapping("/test")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun sendTestMessage(
        @RequestBody requestBody: RequestBodyDto
    ) {
        exampleStringProducer.sendStringMessage(
            message = requestBody.message
        )
        
    }

    data class RequestBodyDto(val message: String)
}