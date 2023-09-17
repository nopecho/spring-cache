package io.nopecho.cache.global.config

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "cache")
class CacheProperties(
    val redis: List<CacheRedisProperties> = listOf()
)