package org.jesperancinha

import io.kotest.matchers.collections.shouldBeEmpty
import io.kotest.matchers.collections.shouldContain
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class ParserTest {
    @Test
    fun `should run combined parsers`() {
        val woodCompanyInitialsParser = charCountParser('W')
            .then(charCountParser('C'))
            .then(charCountParser('M'))
        val woodCompanyDescriptionTextParser: TextParser<String> = stringCountParser(" Cats Woodlands United")
        val woodCompanyCombinedParser = woodCompanyInitialsParser.then(woodCompanyDescriptionTextParser)
        val input = """
        The WCM Cats Woodlands United association loves cats. 
        As a Wood Company Masters, they create beautiful scratch poles for cats.
        If you have Cats you'll find that they particularly love these poles.
        Maine Coon Cats Woodlands United helps cats around the world achieve their best version of themselves.
        Come to WCM Cats Woodlands United 
        """.trimIndent()
        val result = woodCompanyCombinedParser(input)
        result.count shouldBe 0
        result.remainder.shouldBeEmpty()
    }

    @Test
    fun `should run combined parsers 2`() {
        val woodCompanyInitialsParser = charCountParser('W')
            .then(charCountOnStartParser('C'))
            .then(charCountOnStartParser('M'))
        val woodCompanyDescriptionTextParser: TextParser<String> = stringCountOnStartParser(" Cats Woodlands United")
        val woodCompanyCombinedParser = woodCompanyInitialsParser.then(woodCompanyDescriptionTextParser)
        val input = """
        The WCM Cats Woodlands United association loves cats. 
        As a Wood Company Masters, they create beautiful scratch poles for cats.
        If you have Cats you'll find that they particularly love these poles.
        Maine Coon Cats Woodlands United helps cats around the world achieve their best version of themselves.
        Come to WCM Cats Woodlands United 
        """.trimIndent()
        val result = woodCompanyCombinedParser(input)
        result.count shouldBe 0
        result.remainder.shouldBeEmpty()
    }

    @Test
    fun `should run combined parsers 3`() {
        val woodCompanyInitialsParser = charCountParser('W')
            .then(charCountOnStartParser('C'))
            .then(charCountOnStartParser('M'))
        val woodCompanyDescriptionTextParser: TextParser<String> = stringCountParser(" Cats Woodlands United")
        val woodCompanyCombinedParser = woodCompanyDescriptionTextParser.then(woodCompanyInitialsParser)
        val input = """
        The WCM Cats Woodlands United association loves cats. 
        As a Wood Company Masters, they create beautiful scratch poles for cats.
        If you have Cats you'll find that they particularly love these poles.
        Maine Coon Cats Woodlands United helps cats around the world achieve their best version of themselves.
        Come to WCM Cats Woodlands United 
        """.trimIndent()
        val result = woodCompanyCombinedParser(input)
        result.count shouldBe 2
        result.remainder.shouldBeEmpty()
        result.expected shouldBe (" Cats Woodlands United" to (('W' to 'C') to 'M'))
        println(result)
    }

    @Test
    fun `should run combined parsers on or`() {
        val woodCompanyInitialsParser = charCountParser('W')
            .or(charCountParser('C'))
            .or(charCountParser('M'))
        val woodCompanyDescriptionTextParser: TextParser<String> = stringCountParser(" Cats Woodlands United")
        val woodCompanyCombinedParser = woodCompanyDescriptionTextParser or (woodCompanyInitialsParser)
        val input = """
        The WCM Cats Woodlands United association loves cats. 
        As a Wood Company Masters, they create beautiful scratch poles for cats.
        If you have Cats you'll find that they particularly love these poles.
        Maine Coon Cats Woodlands United helps cats around the world achieve their best version of themselves.
        Come to WCM Cats Woodlands United 
        """.trimIndent()
        val result = woodCompanyCombinedParser(input)
        result.count shouldBe 22
        result.remainder shouldContain "The WCM"
        result.expected shouldBe mapOf(" Cats Woodlands United" to mapOf((mapOf('W' to 'C')) to 'M'))
        println(result)
    }
}
