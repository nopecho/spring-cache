package io.nopecho.cache.local.config

import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.cache.CacheManager
import org.springframework.cache.annotation.EnableCaching
import org.springframework.cache.support.SimpleCacheManager
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
@EnableCaching
@ConfigurationPropertiesScan(basePackages = ["io.nopecho.cache"])
class CacheConfig(
    val caffeineCacheConfig: CaffeineCacheConfig
) {

    @Bean
    fun caffeineManager(): CacheManager {
        val manager = SimpleCacheManager()
        manager.setCaches(caffeineCacheConfig.getCaffeineCaches())
        return manager
    }
}