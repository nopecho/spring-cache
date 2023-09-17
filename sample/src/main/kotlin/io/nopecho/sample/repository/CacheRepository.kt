package io.nopecho.sample.repository

import org.springframework.stereotype.Repository


@Repository
class CacheRepository(
    private val map: MutableMap<String, Any> = mutableMapOf()
) {

    fun findById(id: String): Any {
        return map[id] ?: throw NoSuchElementException("not found")
    }

    fun save(id: String, value: String) {
        map[id] = value
    }
}