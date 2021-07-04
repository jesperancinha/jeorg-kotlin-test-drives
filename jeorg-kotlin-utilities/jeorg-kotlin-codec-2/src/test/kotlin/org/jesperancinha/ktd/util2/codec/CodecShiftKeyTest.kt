package org.jesperancinha.ktd.util2.codec

import io.kotest.core.spec.style.WordSpec
import io.kotest.matchers.shouldBe

class CodecShiftKeyTest : WordSpec() {

    init {
        "main" should {
            main(arrayListOf())
        }

        "testEncode" should {
            val encoded = CodecShiftKey.encode("anaconda", 25)
            encoded shouldBe "zmzbnmcz"
        }

        "testDecode" should {
            val encoded = CodecShiftKey.decode("zmzbnmcz", 25)
            encoded shouldBe "anaconda"
        }
    }
}
