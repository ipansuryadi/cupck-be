package com.cupcake.learn.be.fcm

import com.google.auth.oauth2.GoogleCredentials
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import com.google.firebase.messaging.FirebaseMessaging
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.io.IOException

@Configuration
@EnableConfigurationProperties(FirebaseProperties::class)
class FirebaseConfiguration(private val firebaseProperties: FirebaseProperties) {
    @Bean
    fun googleCredentials(): GoogleCredentials {
        try {
            if (firebaseProperties.serviceAccount != null) {
                firebaseProperties.serviceAccount!!.inputStream.use { `is` ->
                    return GoogleCredentials.fromStream(`is`)
                }
            } else {
                // Use standard credentials chain. Useful when running inside GKE
                return GoogleCredentials.getApplicationDefault()
            }
        } catch (ioe: IOException) {
            throw RuntimeException(ioe)
        }
    }

    @Bean
    fun firebaseApp(credentials: GoogleCredentials?): FirebaseApp {
        val options =
            FirebaseOptions.builder()
                .setCredentials(credentials)
                .build()

        return FirebaseApp.initializeApp(options)
    }

    @Bean
    fun firebaseMessaging(firebaseApp: FirebaseApp?): FirebaseMessaging {
        return FirebaseMessaging.getInstance(firebaseApp)
    }
}
