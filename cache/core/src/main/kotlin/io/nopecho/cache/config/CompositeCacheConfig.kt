package io.nopecho.cache.config

import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.cache.CacheManager
import org.springframework.cache.annotation.EnableCaching
import org.springframework.cache.support.CompositeCacheManager
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary

@Configuration
@EnableCaching
@ConfigurationPropertiesScan(basePackages = ["io.nopecho.cache"])
class CompositeCacheConfig(
    @Qualifier("localCacheManager")
    private val localCacheManager: CacheManager,
    @Qualifier("globalCacheManager")
    private val globalCacheManager: CacheManager,
) {

    @Primary
    @Bean
    fun compositeCacheManager(): CacheManager {
        val compositeCacheManager = CompositeCacheManager(
            localCacheManager,
            globalCacheManager
        )
        compositeCacheManager.setFallbackToNoOpCache(true)
        return compositeCacheManager
    }
}