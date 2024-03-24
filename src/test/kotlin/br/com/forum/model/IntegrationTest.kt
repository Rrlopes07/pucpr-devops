package br.com.forum.model

import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.testcontainers.containers.GenericContainer
import org.testcontainers.containers.PostgreSQLContainer
import org.testcontainers.containers.wait.strategy.Wait
import org.testcontainers.containers.wait.strategy.WaitAllStrategy
import java.time.Duration

interface IntegrationTest {

    companion object {

        val containerPostgres =
            PostgreSQLContainer("postgres:16.2")
                .apply {
                    withDatabaseName("testedb")
                    withUsername("teste")
                    withPassword("123456")
                    withReuse(true)
                }

        val containerRedis =
            GenericContainer("redis:7.2.4")
                .apply { withExposedPorts(6379) }

        @JvmStatic
        @DynamicPropertySource
        fun properties(registry: DynamicPropertyRegistry) =
            registry.apply {
                registry.add("spring.datasource.url", containerPostgres::getJdbcUrl)
                registry.add("spring.datasource.password", containerPostgres::getPassword)
                registry.add("spring.datasource.username", containerPostgres::getUsername)

                registry.add("spring.redis.host", containerRedis::getHost)
                registry.add("spring.redis.port", containerRedis::getFirstMappedPort)
            }

        @JvmStatic
        @BeforeAll
        fun init() {
            containerPostgres.start()
            containerRedis.start()
        }

    }

}