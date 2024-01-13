import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class Others {

    @Test
    fun `should running reduce correctly`() {
        val testString = "That fish doesn't like strings"
        val result = testString
            .runningReduce { acc, c ->
                c
                    .takeIf { c > acc } ?: acc
            }
            .joinToString("")

        val result2 = testString
            .reduce { acc, c ->
                c
                    .takeIf { c > acc } ?: acc
            }
        result shouldBe "Thh".plus("t".repeat(27))
        result.length shouldBe testString.length
        result2 shouldBe 't'
    }
}