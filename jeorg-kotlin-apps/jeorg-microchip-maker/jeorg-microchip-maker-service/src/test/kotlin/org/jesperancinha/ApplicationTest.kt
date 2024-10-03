package org.jesperancinha

import io.ktor.server.routing.*
import io.ktor.http.*
import io.ktor.server.auth.*
import io.ktor.util.*
import io.ktor.server.auth.jwt.*
import com.auth0.jwt.JWT
import com.auth0.jwt.JWTVerifier
import com.auth0.jwt.algorithms.Algorithm
import io.ktor.serialization.jackson.*
import com.fasterxml.jackson.databind.*
import io.ktor.server.plugins.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.request.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import kotlin.test.*
import io.ktor.server.testing.*
import org.jesperancinha.plugins.*
import java.time.LocalDateTime
import java.time.ZoneId

class ApplicationTest {
    @Test
    fun testRoot() = testApplication {
        application {
            configureRouting()
        }
        client.get("/").apply {
            assertEquals(HttpStatusCode.OK, status)
            assertEquals("Hello World!", bodyAsText())
        }
    }


    /**
     * Should test local datetime
     * Test created to show that LocalDatetime can be used with only a timestamp.
     */
    @Test
    fun `should test LocalDatetime`() {
        var ldt = LocalDateTime.now()
        println(ldt)
        val zoned = ldt.atZone(ZoneId.systemDefault())
        val instant = zoned.toInstant()
        ldt = instant.atZone(ZoneId.of("Asia/Kolkata")).toLocalDateTime()
        println(ldt)
    }
}