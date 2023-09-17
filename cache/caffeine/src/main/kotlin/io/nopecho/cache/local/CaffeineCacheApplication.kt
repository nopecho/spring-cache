package io.nopecho.cache.local

import org.slf4j.LoggerFactory
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class CaffeineCacheApplication

fun main(args: Array<String>) {
    runApplication<CaffeineCacheApplication>(*args)
}

inline fun <reified T> T.log() = LoggerFactory.getLogger(T::class.java)!!