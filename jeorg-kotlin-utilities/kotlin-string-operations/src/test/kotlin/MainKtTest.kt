import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.shouldHaveLength
import io.kotest.matchers.string.shouldStartWith
import org.jesperancinha.main
import org.junit.jupiter.api.Test

class MainKtTest {


    @Test
    fun `should test main`() {
        main()
    }

    @Test
    fun `should fail using empty identations`() {
        shouldThrow<IllegalArgumentException> {
            val testString =
                "   The wolf, the fish,"
                    .plus("and the print shop")
            val resultString = testString
                .replaceIndentByMargin(
                    " ---",
                    "   "
                )
            println(
                resultString
            )
        }
    }

    @Test
    fun `should succeed using non-empty identation but with an empty prefix`() {
        val resultString = test1()
        val resultString2 = test2()
        resultString shouldBe resultString2

    }

    private fun test2(): String {
        val testString2 = "---I want to start at the beginning with dashes"
        val resultString2 = testString2
            .replaceIndentByMargin(">>>", "---")
        println(
            resultString2
        )
        resultString2.shouldHaveLength(47)
        resultString2.shouldStartWith(">")
        return resultString2
    }

    private fun test1(): String {
        val testString = " ---I want to start at the beginning with dashes"
        val resultString = testString
            .replaceIndentByMargin(">>>", "---")
        println(
            resultString
        )
        resultString.shouldHaveLength(47)
        resultString.shouldStartWith(">")
        return resultString
    }
}