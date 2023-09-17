package io.nopecho.cache.global.config

data class CacheRedisSpecProperties(
    val name: String = "",
    val ttl: Long = DEFAULT_TTL,
) {
    companion object {
        val DEFAULT_TTL: Long = 1800
    }
}