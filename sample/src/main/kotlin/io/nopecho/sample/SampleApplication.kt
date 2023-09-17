package io.nopecho.sample

import org.slf4j.LoggerFactory
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication(scanBasePackages = ["io.nopecho"])
class SampleApplication

fun main(args: Array<String>) {
    runApplication<SampleApplication>(*args)
}

inline fun <reified T> T.log() = LoggerFactory.getLogger(T::class.java)!!