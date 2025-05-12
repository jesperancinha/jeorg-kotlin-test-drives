package org.jesperancinha.test.drive.xml

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import java.io.ByteArrayInputStream
import java.io.IOException
import java.nio.charset.Charset

private const val LARS_RESPONSE_1 =
    "Lars: And Sir, with all due respect, it's been over 6 moths since I've sent you this email. If this is your interpretation of my email, then I'm worried about how fast you interpreted it wrong and about why didn't you approached me before about this."

private const val LARS_RESPONSE_2 =
    "Lars: Mr. Moon, all I said in my email is that I wanted to talk to you about the issue because I just wanted to double check with you if you saw the issue the same way as we did, given the way the discussions have been going on the past few weeks."

private const val FENESTEV_MOON_REMARK =
    "Fenestev Moon: I am very insulted by your email. You are questioning my knowledge!"

/**
 * If you checked into this code, then the characters of this code are fictional characters of my Good Story series spread-out throughout articles and repos.
 * It's all good fun, and it's all fiction.
 * In this case the characters are:
 * 1. Fenestev Moon - The Director of MFMF BV in the Netherlands
 * 2. Lars Pereira - A Norwegian colleague working as an Expat in NL
 */
class XmlBookParserBuilderTest {

    @Test
    fun `should close stream on close`() {
        val anInputStream =
            FENESTEV_MOON_REMARK.byteInputStream()
        anInputStream.available().shouldBe(82)
        val parser = createAnonymousParser(anInputStream)
        parser.closeStream()
        anInputStream.use {
            String(it.readAllBytes(), Charset.defaultCharset())
        }.shouldBe(FENESTEV_MOON_REMARK)
        shouldThrow<IOException> { parser.closeStream() }
    }

    @Test
    fun `should parse before closing`() {
        val anInputStream = LARS_RESPONSE_1.byteInputStream()
        val anInputStream2 = LARS_RESPONSE_2.byteInputStream()
        anInputStream.available().shouldBe(250)
        anInputStream2.available().shouldBe(247)
        createAnonymousParser(anInputStream).shouldNotBeNull()
        createAnonymousParser(anInputStream2).shouldNotBeNull()
        anInputStream.use {
            String(it.readAllBytes(), Charset.defaultCharset())
        }.shouldBe(LARS_RESPONSE_1)
        anInputStream2.use {
            String(it.readAllBytes())
        }.shouldBe(LARS_RESPONSE_2)
    }

    private fun createAnonymousParser(anInputStream: ByteArrayInputStream) =
        object : XmlBookParserBuilder(anInputStream) {
            override fun getBookTitle(bookNumber: Int): String? {
                TODO("Not yet implemented")
            }

            override fun getNumberOfPages(bookNumber: Int): Int {
                TODO("Not yet implemented")
            }

            override fun getGenre(bookNumber: Int): String? {
                TODO("Not yet implemented")
            }

            override fun getAuthor(bookNumber: Int): String? {
                TODO("Not yet implemented")
            }

            override val numberOfBooks: Int
                get() = TODO("Not yet implemented")

            override fun init() {
                TODO("Not yet implemented")
            }
        }

}