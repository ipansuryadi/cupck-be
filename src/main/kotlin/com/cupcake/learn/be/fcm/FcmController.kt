package com.cupcake.learn.be.fcm

import com.google.firebase.messaging.*
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*
import java.util.stream.Collectors


@RestController
class FirebasePublisherController(private val fcm: FirebaseMessaging) {
    @PostMapping("/topics/{topic}")
    @Throws(FirebaseMessagingException::class)
    fun postToTopic(@RequestBody message: String?, @PathVariable("topic") topic: String?): ResponseEntity<String> {
        val msg = Message.builder()
            .setTopic(topic)
            .putData("body", message)
            .build()

        val id = fcm.send(msg)
        return ResponseEntity
            .status(HttpStatus.ACCEPTED)
            .body(id)
    }

    @PostMapping("/condition")
    @Throws(FirebaseMessagingException::class)
    fun postToCondition(@RequestBody message: ConditionMessageRepresentation): ResponseEntity<String> {
        val msg = Message.builder()
            .setCondition(message.condition)
            .putData("body", message.data)
            .build()

        val id = fcm.send(msg)
        return ResponseEntity
            .status(HttpStatus.ACCEPTED)
            .body(id)
    }


    @PostMapping("/clients/{registrationToken}")
    @Throws(FirebaseMessagingException::class)
    fun postToClient(
        @RequestBody message: String?,
        @PathVariable("registrationToken") registrationToken: String?
    ): ResponseEntity<String> {
        val msg = Message.builder()
            .setToken(registrationToken)
            .putData("body", message)
            .build()

        val id = fcm.send(msg)
        return ResponseEntity
            .status(HttpStatus.ACCEPTED)
            .body(id)
    }

    @PostMapping("/clients")
    @Throws(FirebaseMessagingException::class)
    fun postToClients(@RequestBody message: MulticastMessageRepresentation): ResponseEntity<List<String>> {
        val msg = MulticastMessage.builder()
            .addAllTokens(message.registrationTokens)
            .putData("body", message.data)
            .build()

        val response = fcm.sendMulticast(msg)

        val ids = response.responses
            .stream()
            .map { r: SendResponse -> r.messageId }
            .collect(Collectors.toList())

        return ResponseEntity
            .status(HttpStatus.ACCEPTED)
            .body(ids)
    }

    @PostMapping("/subscriptions/{topic}")
    @Throws(FirebaseMessagingException::class)
    fun createSubscription(
        @PathVariable("topic") topic: String?,
        @RequestBody registrationTokens: List<String?>?
    ): ResponseEntity<Void> {
        fcm.subscribeToTopic(registrationTokens, topic)
        return ResponseEntity.ok().build()
    }

    @DeleteMapping("/subscriptions/{topic}/{registrationToken}")
    @Throws(FirebaseMessagingException::class)
    fun deleteSubscription(
        @PathVariable topic: String?,
        @PathVariable registrationToken: String
    ): ResponseEntity<Void> {
        fcm.subscribeToTopic(Arrays.asList(registrationToken), topic)
        return ResponseEntity.ok().build()
    }
}