package org.jesperancinha.ktd.json1

import io.kotest.core.spec.style.WordSpec
import io.kotest.matchers.collections.shouldBeIn
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.longs.shouldBeGreaterThanOrEqual
import io.kotest.matchers.longs.shouldBeInRange
import io.kotest.matchers.longs.shouldBeLessThanOrEqual
import io.kotest.matchers.string.shouldHaveLength
import io.kotest.matchers.string.shouldMatch
import org.jesperancinha.ktd.json1.model.Currency
import java.time.LocalDate

class StampsAndCoinsGeneratorKtTest : WordSpec() {

    init {
        "getRandomDimMM" should {
            "Get dim between considered range" {
                getRandomDimMM(1, 10) shouldBeInRange (LongRange(1, 10))
            }
        }

        "getRandomDiameterMM" should {
            getRandomDiameterMM(1, 10) shouldBeInRange (LongRange(1, 10))
        }

        "getRandomCurrency" should {
            getRandomCurrency() shouldBeIn Currency.values()
        }

        "getRandomValueUpTo" should {
            getRandomValueUpTo(10) shouldBeInRange LongRange(1, 10)
        }

        "getRandomYearFrom" should {
            getRandomYearFrom(1900) shouldBeGreaterThanOrEqual 1900
            getRandomYearFrom(1900) shouldBeLessThanOrEqual LocalDate.now().year.toLong()
        }

        "getRandomNameWithChars" should {
            getRandomNameWithChars(100) shouldHaveLength 100
            getRandomNameWithChars(100) shouldMatch "[a-z0-9\\ ]*"
        }

        "list generation" should {
            "generate list of 10 stamps" {
                val listOfStamps = createListOfStamps(10)
                listOfStamps shouldHaveSize 10
            }
            "generate list of 10 coins" {
                val listOfCoins = createListOfCoins(10)
                listOfCoins shouldHaveSize 10
            }
        }

    }
}
