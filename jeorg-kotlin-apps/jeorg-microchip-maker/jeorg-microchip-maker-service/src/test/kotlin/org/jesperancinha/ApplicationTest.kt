package org.jesperancinha

import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.server.testing.*
import org.jesperancinha.plugins.configureRouting
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZoneOffset
import kotlin.test.Test
import kotlin.test.assertEquals

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
        val ldt = LocalDateTime.now().also { println(it) }
        
        val ldt2 = ldt.atZone(ZoneId.systemDefault())
            .toInstant()
            .atZone(ZoneId.of("Asia/Kolkata"))
            .toLocalDateTime()
            .also { println(it) }
            
        with(ZoneOffset.UTC) {
            println(ldt.toInstant(this))
            println(ldt2.toInstant(this))
        }
    }
}