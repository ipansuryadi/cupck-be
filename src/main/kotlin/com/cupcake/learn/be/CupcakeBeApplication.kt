package com.cupcake.learn.be

import mu.KotlinLogging
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.config.EnableMongoAuditing
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.mongodb.repository.Query
import org.springframework.http.ResponseEntity
import org.springframework.scheduling.annotation.EnableAsync
import org.springframework.stereotype.Repository
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.*

@ConfigurationPropertiesScan
@EnableMongoAuditing
@EnableAsync
@SpringBootApplication
class CupcakeBeApplication

private val logger = KotlinLogging.logger({})

fun main(args: Array<String>) {
    runApplication<CupcakeBeApplication>(*args)
    logger.info { "Cupcake Back-End Learning Program is running!" }
}

@Document("person")
data class Person(
    @Id
    val id: String,
    @Field(name = "name")
    @Indexed(unique = true)
    var name: String,
)

@Repository
interface MainRepository : MongoRepository<Person, String> {
    @Query("{'name':?0}")
    fun findByName(name: String): Optional<Person>
}

@Service
class MainService(
    @Autowired val mainRepository: MainRepository,
) {
    fun getName(name: String): Person = mainRepository.findByName(name).orElseThrow { throw RuntimeException("Cannot fond name") }
}

@RestController
class MainController(
    @Autowired val mainService: MainService,
) {
    @GetMapping("/")
    fun index(
        @RequestParam("name") name: String,
    ) = ResponseEntity.ok(mainService.getName(name))
}
