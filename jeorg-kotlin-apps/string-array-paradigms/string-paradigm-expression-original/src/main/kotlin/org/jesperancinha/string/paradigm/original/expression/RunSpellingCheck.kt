package org.jesperancinha.string.paradigm.original.expression

object RunSpellingCheck {
    fun findSimilarRegions(
        componentsA: Collection<RegionImpl>,
        componentsB: Collection<RegionImpl>
    ): Set<RegionImpl>? {
        // TODO To be implemented
        return null
    }
}

fun main() {
    val regions = arrayOf("Algarve", "minho", " Ribatejo")
    val assignedNumbers = arrayOf("5'", "6'", ",2", "3'", "7'", ",1")
    val components: MutableList<RegionImpl> = ArrayList()
    for (type in regions) {
        for (size in assignedNumbers) {
            components.add(RegionImpl(type, size))
        }
    }
    val check = SpellingCheck()
    for (type in regions) {
        check.checkSpelling(type)
    }
    for (size in assignedNumbers) {
        check.checkSpelling(size)
    }
    val incorrect: MutableList<String?> = ArrayList()
    incorrect.addAll(check.spellingDoubleCommaRegions)
    incorrect.addAll(check.spellingBeginCapitalRegions)
    incorrect.addAll(check.spellingWhiteSpacesRegions)
    println("Incorrect attributes: $incorrect")
    val componentCheck = RegionSpellingCheck()
    for (component in components) {
        componentCheck.checkSpelling(component)
    }
    val incorrectRegions: MutableList<RegionImpl?> = ArrayList()
    incorrectRegions.addAll(componentCheck.spellingDoubleCommaRegions)
    incorrectRegions.addAll(componentCheck.spellingBeginCapitalRegions)
    incorrectRegions.addAll(componentCheck.spellingWhiteSpacesRegions)
    println("Incorrect components:")
    for (component in incorrectRegions) {
        println(component?.description)
    }
    val set: MutableSet<RegionImpl> = HashSet()
    set.add(RegionImpl(regions[1], assignedNumbers[0]))
    set.add(RegionImpl(regions[1], assignedNumbers[0]))
    set.add(RegionImpl(regions[1], assignedNumbers[2]))
    println("Similar components: " + RunSpellingCheck.findSimilarRegions(components, set))
}
