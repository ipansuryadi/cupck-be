package com.cupcake.learn.be.fcm

import com.google.firebase.messaging.*
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*


@RestController
class FirebasePublisherController(private val fcm: FirebaseMessaging) {
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
}