package io.nopecho.cache.local.config

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "cache")
class CacheProperties(
    val caffeine: List<CaffeineProperties> = listOf()
)