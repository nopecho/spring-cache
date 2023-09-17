package io.nopecho.sample.service

import io.nopecho.sample.repository.CacheRepository
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Service

@Service
class CacheService(
    private val repository: CacheRepository
) {


    @Cacheable(
        cacheManager = "localCacheManager",
        cacheNames = ["localTest"],
        key = "#id"
    )
    fun getAnyByCaffeine(id: String): Any {
        return repository.findById(id)
    }


    @Cacheable(
        cacheManager = "globalCacheManager",
        cacheNames = ["redisCache"],
        key = "#id"
    )
    fun getAnyByRedis(id: String): Any {
        return repository.findById(id)
    }

    fun put(id: String, value: String) {
        repository.save(id, value)
    }
}