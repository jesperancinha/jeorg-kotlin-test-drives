package org.jesperancinha.ktd.util1.codec

import io.kotest.core.spec.style.WordSpec
import io.kotest.matchers.comparables.shouldBeEqualComparingTo

/**
 * Created by jofisaes on 03/07/2021
 */
class Codec16AsciiTest : WordSpec() {

    init {
        "Test codec 16" should {
            "testEncodeA"{
                val encoded = Codec16Ascii.encode('A')
                encoded shouldBeEqualComparingTo (1040)
            }

            "testDecodeA"{
                val decoded = Codec16Ascii.decode(1040)
                decoded shouldBeEqualComparingTo 'A'
            }

            "testDecodeString"{
                val decoded = Codec16Ascii.decode(
                    "1344 1664 1616 512 1664 1680 1728 1728 1840 512 1552 " +
                            "1824 1616 512 1552 1728 1680 1888 1616 512 1904 1680 1856 " +
                            "1664 512 1856 1664 1616 512 1840 1776 1872 1760 1600 512 " +
                            "1776 1632 512 1744 1872 1840 1680 1584"
                )
                decoded shouldBeEqualComparingTo "The hills are alive with the sound of music"
            }

            "testEncodeString"{
                val encoded = Codec16Ascii.encode("The hills are alive with the sound of music")
                encoded shouldBeEqualComparingTo
                        "1344 1664 1616 512 1664 1680 1728 1728 1840 512 1552 " +
                        "1824 1616 512 1552 1728 1680 1888 1616 512 1904 1680 " +
                        "1856 1664 512 1856 1664 1616 512 1840 1776 1872 1760 " +
                        "1600 512 1776 1632 512 1744 1872 1840 1680 1584"

            }
        }
    }
}