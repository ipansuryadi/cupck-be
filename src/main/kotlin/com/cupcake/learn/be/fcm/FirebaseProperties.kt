package com.cupcake.learn.be.fcm

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.core.io.Resource

@ConfigurationProperties(prefix = "gcp.firebase")
class FirebaseProperties {
    /**
     * @return the serviceAccount
     */
    /**
     * @param serviceAccount the serviceAccount to set
     */
    var serviceAccount: Resource? = null
}
