package org.jesperancinha.ktd

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import io.kotest.matchers.shouldBe
import jakarta.validation.Validation.buildDefaultValidatorFactory
import jakarta.validation.Validator
import org.jesperancinha.ktd.java.BookBuilder
import kotlin.test.Test


class BookTest {

    private val objectMapper: ObjectMapper = ObjectMapper()

    @Test
    fun `should serialize incorrectly for Book`() {
        val book = Book(pages = 200)
        val valueAsString = objectMapper.writeValueAsString(book)
        objectMapper.readValue<Book>(valueAsString)

        val factory = buildDefaultValidatorFactory()
        val validator: Validator = factory.validator

        val validate = validator.validate(book)
        validate.size.shouldBe(1)
    }

    @Test
    fun `should serialize correctly for Book`() {
        val book = Book(pages = 15)
        val valueAsString = objectMapper.writeValueAsString(book)
        objectMapper.readValue<Book>(valueAsString)

        val factory = buildDefaultValidatorFactory()
        val validator: Validator = factory.validator

        val validate = validator.validate(book)
        validate.size.shouldBe(0)
    }


    @Test
    fun `should never fail when off limits for BadBook`() {
        val book = BadBook(pages = 200)
        val valueAsString = objectMapper.writeValueAsString(book)
        objectMapper.readValue<Book>(valueAsString)

        val factory = buildDefaultValidatorFactory()
        val validator: Validator = factory.validator

        val validate = validator.validate(book)
        validate.size.shouldBe(0)
    }


    @Test
    fun `should never fail when in limits for BadBook`() {
        val book = BadBook(pages = 15)
        val valueAsString = objectMapper.writeValueAsString(book)
        objectMapper.readValue<Book>(valueAsString)

        val factory = buildDefaultValidatorFactory()
        val validator: Validator = factory.validator

        val validate = validator.validate(book)
        validate.size.shouldBe(0)
    }

    @Test
    fun `should create Java Book`(){
        val book = BookBuilder().withPages(15)

        val factory = buildDefaultValidatorFactory()
        val validator: Validator = factory.validator

        val validate = validator.validate(book)

        validate.size.shouldBe(0)
    }
    @Test
    fun `should not create Java Book`(){
        val book = BookBuilder().withPages(200).build()

        val factory = buildDefaultValidatorFactory()
        val validator: Validator = factory.validator

        val validate = validator.validate(book)

        validate.size.shouldBe(1)
    }

}