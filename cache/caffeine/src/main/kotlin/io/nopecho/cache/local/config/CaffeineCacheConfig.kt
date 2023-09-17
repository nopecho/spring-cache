package io.nopecho.cache.local.config

import com.github.benmanes.caffeine.cache.Caffeine
import org.springframework.cache.CacheManager
import org.springframework.cache.caffeine.CaffeineCache
import org.springframework.cache.support.SimpleCacheManager
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.util.concurrent.TimeUnit

@Configuration
class CaffeineCacheConfig(
    private val cacheProperties: CacheProperties
) {

    @Bean
    fun localCacheManager(): CacheManager {
        val manager = SimpleCacheManager()
        manager.setCaches(getCaffeineCaches())
        return manager
    }

    private fun getCaffeineCaches(): List<CaffeineCache> {
        return cacheProperties.caffeine.map {
            CaffeineCache(
                it.spec.name,
                Caffeine.newBuilder()
                    .maximumSize(it.spec.maxSize)
                    .expireAfterWrite(it.spec.expireTimeAfterWrite, TimeUnit.SECONDS)
                    .build()
            )
        }
    }
}