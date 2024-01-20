package com.cupcake.learn.be.kafkaexample

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
class ExampleController(
    private val exampleStringProducer: ExampleStringProducer,
) {
    @PostMapping("/test")
    @ResponseStatus(HttpStatus.OK)
    fun sendTestMessage(
        @RequestBody requestBody: RequestBodyDto,
    ): ResponseEntity<ResponseBodyDto> {
        exampleStringProducer.sendStringMessage(
            message = requestBody.message,
        )

        val responseBody = ResponseBodyDto("success", requestBody.message)

        return ResponseEntity.ok(responseBody)
    }

    data class RequestBodyDto(val message: String)

    data class ResponseBodyDto(val messageResponse: String, val messageReceived: String)
}
