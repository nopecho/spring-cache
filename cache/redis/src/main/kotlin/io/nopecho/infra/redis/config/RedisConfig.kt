package io.nopecho.infra.redis.config

import org.springframework.boot.autoconfigure.data.redis.RedisProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.connection.RedisStandaloneConfiguration
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.serializer.StringRedisSerializer

@Configuration
class RedisConfig(
    private val redisProperties: RedisProperties,
) {

    @Bean
    fun redisConnectionFactory(): RedisConnectionFactory {
        val configuration = RedisStandaloneConfiguration()
        configuration.hostName = redisProperties.host
        configuration.port = redisProperties.port
        configuration.username = redisProperties.username
        configuration.setPassword(redisProperties.password)
        return LettuceConnectionFactory(configuration)
    }

    @Bean
    fun redisTemplate(): RedisTemplate<*, *> {
        val redisTemplate: RedisTemplate<*, *> = RedisTemplate<Any, Any>()
        redisTemplate.connectionFactory = redisConnectionFactory()
        redisTemplate.setEnableTransactionSupport(true)
        redisTemplate.setDefaultSerializer(StringRedisSerializer())
        return redisTemplate
    }
}