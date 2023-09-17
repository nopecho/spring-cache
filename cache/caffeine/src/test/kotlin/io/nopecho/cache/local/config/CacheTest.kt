package io.nopecho.cache.local.config

import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.cache.annotation.CacheConfig
import org.springframework.cache.annotation.EnableCaching
import org.springframework.context.annotation.Import
import org.springframework.test.context.junit.jupiter.SpringExtension

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@ExtendWith(SpringExtension::class)
@Import(value = [CacheConfig::class, CaffeineCacheConfig::class])
@EnableCaching
annotation class CacheTest
