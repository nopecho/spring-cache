package io.nopecho.cache.local.config

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.cache.annotation.Cacheable
import org.springframework.context.annotation.Import
import org.springframework.stereotype.Service


@CacheTest
@SpringBootTest
@Import(FakeCacheService::class)
class CacheConfigTest {

    @Autowired
    lateinit var service: FakeCacheService

    @Test
    fun cacheTest() {
        service.setter("1", "2")
        service.getter("1")
        service.getter("1")
        service.getter("1")
        service.getter("1")

        assertThat(service.callCount).isEqualTo(1)
    }
}


@Service
class FakeCacheService(
    private val map: MutableMap<String, String> = mutableMapOf(),
    var callCount: Int = 0
) {

    @Cacheable(
        cacheManager = "caffeineManager",
        cacheNames = ["test"],
        key = "#id",
    )
    fun getter(id: String): String? {
        callCount += 1
        return map[id]
    }

    fun setter(id: String, value: String) {
        map[id] = value
    }
}