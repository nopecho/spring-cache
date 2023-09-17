package io.nopecho.cache.config

import io.nopecho.cache.global.config.RedisCacheConfig
import io.nopecho.cache.local.config.CaffeineCacheConfig
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.cache.CacheManager
import org.springframework.cache.annotation.EnableCaching
import org.springframework.cache.support.CompositeCacheManager
import org.springframework.cache.support.SimpleCacheManager
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.data.redis.cache.RedisCacheManager

@Configuration
@EnableCaching
@ConfigurationPropertiesScan(basePackages = ["io.nopecho.cache"])
class CompositeCacheConfig(
    private val caffeineCacheConfig: CaffeineCacheConfig,
    private val redisCacheConfig: RedisCacheConfig,
) {

    @Primary
    @Bean
    fun compositeCacheManager(): CacheManager {
        val compositeCacheManager = CompositeCacheManager(
            localCacheManager(),
            globalCacheManager()
        )
        compositeCacheManager.setFallbackToNoOpCache(true)
        return compositeCacheManager
    }

    @Bean
    fun globalCacheManager(): CacheManager {
        return RedisCacheManager.builder(redisCacheConfig.getRedisConnection())
            .withInitialCacheConfigurations(redisCacheConfig.getCacheConfigurations())
            .build()
    }

    @Bean
    fun localCacheManager(): CacheManager {
        val manager = SimpleCacheManager()
        manager.setCaches(caffeineCacheConfig.getCaffeineCaches())
        return manager
    }
}