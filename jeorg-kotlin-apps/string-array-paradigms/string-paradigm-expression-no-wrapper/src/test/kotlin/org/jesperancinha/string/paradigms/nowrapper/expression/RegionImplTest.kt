package org.jesperancinha.string.paradigms.nowrapper.expression

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

private const val REGION = "Where the cheeses are made"

private const val REGION_SIZE = "BIG"

class RegionImplTest {

    @Test
    fun `should create region implementation`() {
        val regionImpl = RegionImpl(REGION, REGION_SIZE)
        regionImpl.regionName shouldBe REGION
        regionImpl.assignedNumber shouldBe REGION_SIZE
        regionImpl.description shouldBe "$REGION, $REGION_SIZE"
    }
}