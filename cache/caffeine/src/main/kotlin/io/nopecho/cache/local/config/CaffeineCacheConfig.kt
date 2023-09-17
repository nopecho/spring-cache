package io.nopecho.cache.local.config

import com.github.benmanes.caffeine.cache.Caffeine
import org.springframework.cache.caffeine.CaffeineCache
import org.springframework.context.annotation.Configuration
import java.util.concurrent.TimeUnit

@Configuration
class CaffeineCacheConfig(
    val cacheProperties: CacheProperties
) {

    fun getCaffeineCaches(): List<CaffeineCache> {
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