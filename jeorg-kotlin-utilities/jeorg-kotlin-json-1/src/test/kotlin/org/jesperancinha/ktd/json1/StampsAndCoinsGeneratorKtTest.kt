package org.jesperancinha.ktd.json1

import io.kotest.core.spec.style.WordSpec
import io.kotest.matchers.collections.shouldBeIn
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.comparables.shouldBeEqualComparingTo
import io.kotest.matchers.longs.shouldBeExactly
import io.kotest.matchers.longs.shouldBeGreaterThanOrEqual
import io.kotest.matchers.longs.shouldBeInRange
import io.kotest.matchers.longs.shouldBeLessThanOrEqual
import io.kotest.matchers.string.shouldHaveLength
import io.kotest.matchers.string.shouldMatch
import org.jesperancinha.ktd.json1.model.Coin
import org.jesperancinha.ktd.json1.model.Currency
import org.jesperancinha.ktd.json1.model.Stamp
import java.time.LocalDate

class StampsAndCoinsGeneratorKtTest : WordSpec() {

    init {
        "main call" should {
            "run smoothly" {
                main(arrayOf())
            }
        }

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

        "creating objects" should {
            "create coin with params" {
                val coin = Coin(1, "desc", 1900, 10, Currency.PTE, 10, 0)
                coin.id shouldBeEqualComparingTo 1
                coin.description shouldBeEqualComparingTo "desc"
                coin.year shouldBeEqualComparingTo 1900
                coin.value shouldBeEqualComparingTo 10
                coin.currency shouldBeEqualComparingTo Currency.PTE
                coin.diameterMM shouldBeExactly 10
                coin.internalDiameterMM shouldBeExactly 0
            }

            "create stamp with params" {
                val stamp = Stamp(1, "desc", 1900, 10, Currency.PTE, 10, 20)
                stamp.id shouldBeExactly 1
                stamp.description shouldBeEqualComparingTo "desc"
                stamp.year shouldBeExactly 1900
                stamp.value shouldBeExactly 10
                stamp.currency shouldBeEqualComparingTo Currency.PTE
                stamp.heightMM shouldBeExactly 10
                stamp.widthMM shouldBeExactly 20
            }
        }

    }
}
