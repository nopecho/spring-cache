package io.nopecho.cache.local.config

data class CacheCaffeineSpecProperties(
    val name: String = "",
    val maxSize: Long = -1,
    val expireTimeAfterWrite: Long = 1800
)
