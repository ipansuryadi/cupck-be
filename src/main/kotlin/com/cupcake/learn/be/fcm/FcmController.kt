package com.cupcake.learn.be.fcm

import com.google.firebase.messaging.*
import com.google.gson.Gson
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

data class ModelUsingDataClass(val id: String, val name: String, val age: Number)

@RestController
class FirebasePublisherController(private val fcm: FirebaseMessaging) {
    @PostMapping("/clients/{registrationToken}")
    @Throws(FirebaseMessagingException::class)
    fun postToClient(
        @RequestBody message: String?,
        @PathVariable("registrationToken") registrationToken: String?,
    ): ResponseEntity<String> {
        val model = ModelUsingDataClass(id = "123", name = "ipan", age = 38)
        // data class if we don't want to change val/var, also can use copy for cloning to new instance (method copy doesn't exist in ordinary class):
        val modifiedModel = model.copy(age = 17)
        val gson = Gson()
        val jsonStringFromModel = gson.toJson(modifiedModel)
        val msg =
            Message.builder()
                .setToken(registrationToken)
                .putData("data", jsonStringFromModel)
                .putData("type", "NOTIFICATION_TYPE")
                .build()

        val id = fcm.send(msg)
        return ResponseEntity
            .status(HttpStatus.ACCEPTED)
            .body(id)
    }
}
