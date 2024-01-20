package com.cupcake.learn.be.user.controller

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/users")
class UserController {
    @GetMapping
    fun index(): ResponseEntity<String> {
        return ResponseEntity("Hello", HttpStatus.OK)
    }
}
