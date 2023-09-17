package io.nopecho.cache.global.config

import io.nopecho.cache.global.config.CacheRedisSpecProperties.Companion.DEFAULT_TTL
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.cache.RedisCacheConfiguration
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer
import org.springframework.data.redis.serializer.RedisSerializationContext
import org.springframework.data.redis.serializer.StringRedisSerializer
import java.time.Duration

@Configuration
class RedisCacheConfig(
    private val cacheProperties: CacheProperties,
    private val redisConnection: RedisConnectionFactory
) {

    fun getRedisConnection(): RedisConnectionFactory {
        return redisConnection
    }

    fun getCacheConfigurations(): Map<String, RedisCacheConfiguration> {
        val defaultConfig = RedisCacheConfiguration.defaultCacheConfig()
            .entryTtl(Duration.ofSeconds(DEFAULT_TTL))
            .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(StringRedisSerializer()))
            .serializeValuesWith(
                RedisSerializationContext.SerializationPair.fromSerializer(
                    Jackson2JsonRedisSerializer(
                        Any::class.java
                    )
                )
            )

        val configMap = mutableMapOf<String, RedisCacheConfiguration>()
        cacheProperties.redis.forEach {
            configMap[it.spec.name] = defaultConfig.entryTtl(Duration.ofSeconds(it.spec.ttl))
        }
        return configMap
    }
}