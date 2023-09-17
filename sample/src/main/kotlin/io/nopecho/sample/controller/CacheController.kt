package io.nopecho.sample.controller

import io.nopecho.sample.service.CacheService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class CacheController(
    private val service: CacheService
) {

    @GetMapping("/cache/local/{id}")
    fun test(@PathVariable id: String): ResponseEntity<Any> {
        val result = service.getAnyByCaffeine(id)

        return ResponseEntity.ok(result)
    }

    @GetMapping("/cache/global/{id}")
    fun test2(@PathVariable id: String): ResponseEntity<Any> {
        val result = service.getAnyByRedis(id)

        return ResponseEntity.ok(result)
    }

    @GetMapping("/cache/{id}/{value}")
    fun test3(
        @PathVariable id: String,
        @PathVariable value: String
    ): ResponseEntity<Any> {
        val result = service.put(id, value)

        return ResponseEntity.ok(result)
    }
}