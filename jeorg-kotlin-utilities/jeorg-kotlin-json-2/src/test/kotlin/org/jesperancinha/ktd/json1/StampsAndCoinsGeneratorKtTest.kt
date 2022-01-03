package org.jesperancinha.ktd.json1

import io.kotest.core.spec.style.WordSpec
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import org.jesperancinha.ktd.json2.model.Stamp
import org.jesperancinha.ktd.json2.readValue

class ReadStampKtTest : WordSpec() {

    init {
        "main call" should {
            "run smoothly" {
            }
        }

        "reading a stamp" should {
            val stamp = readValue<Stamp>("/stamps.xml")
            stamp.shouldNotBeNull()
            stamp.id shouldBe 123
            stamp.description shouldBe "government"
        }

    }


}
