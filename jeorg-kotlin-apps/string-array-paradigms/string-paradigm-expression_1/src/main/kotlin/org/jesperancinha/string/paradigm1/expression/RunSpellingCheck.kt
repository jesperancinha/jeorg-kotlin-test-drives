package org.jesperancinha.string.paradigm1.expression

import org.jesperancinha.string.paradigm.api.Region
import java.util.*

object RunSpellingCheck {

    fun findSimilarRegions(
        componentsA: Collection<RegionImpl>,
        componentsB: Collection<RegionImpl>
    ): Set<RegionImpl?> {
        var setToReturn: MutableSet<RegionImpl?> = HashSet()
        setToReturn = similarElementsIteration(componentsA, componentsB, setToReturn)
        setToReturn = similarElementsIteration(componentsB, componentsA, setToReturn)
        println(setToReturn.toTypedArray().contentDeepToString())
        return setToReturn
    }

    /**
     * This implementation relies on reflection. It's not an easy method and it
     * is quite overkill. However it is actually a way of presenting and access
     * unknown object types and this is why it is here. This way we have an
     * example of a refelection based method.
     *
     * @param regionA
     * @param regionB
     * @param setToReturn
     * The similarities between region A and region B
     * @return
     */
    private fun similarElementsIteration(
        regionA: Collection<RegionImpl>,
        regionB: Collection<RegionImpl>, setToReturn: MutableSet<RegionImpl?>
    ): MutableSet<RegionImpl?> {
        val iA = regionA.iterator()
        while (iA.hasNext()) {
            val compareRoot = iA.next()
            val iB = regionB.iterator()
            val rootFields = compareRoot.javaClass.declaredFields
            while (iB.hasNext()) {
                val compareRootTo = iB.next()
                val toFields = compareRootTo.javaClass.declaredFields
                if (Arrays.equals(rootFields, toFields)) {
                    var valuesOk = true
                    try {
                        for (field in toFields) {
                            val fieldType = RegionImpl::class.java.getDeclaredField(field.name)
                            fieldType.isAccessible = true
                            val rootValue = fieldType[compareRoot].toString()
                            val toValue = fieldType[compareRootTo].toString()
                            if (rootValue != toValue) {
                                valuesOk = false
                            }
                        }
                    } catch (e: NoSuchFieldException) {
                        e.printStackTrace()
                    } catch (e: SecurityException) {
                        e.printStackTrace()
                    } catch (e: IllegalArgumentException) {
                        e.printStackTrace()
                    } catch (e: IllegalAccessException) {
                        e.printStackTrace()
                    }
                    if (!setToReturn.contains(compareRoot) && !setToReturn.contains(compareRootTo) && valuesOk) {
                        setToReturn.add(compareRoot)
                    }
                }
            }
        }
        return setToReturn
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
    val check = SpellingCheck<String>()
    for (type in regions) {
        check.checkSpelling(type)
    }
    for (size in assignedNumbers) {
        check.checkSpelling(size)
    }
    val incorrect: MutableList<String> = ArrayList()
    incorrect.addAll(check.spellingDoubleCommaRegions)
    incorrect.addAll(check.spellingBeginCapitalRegions)
    incorrect.addAll(check.spellingWhiteSpacesRegions)
    println("Incorrect attributes: $incorrect")
    val componentCheck = RegionSpellingCheck<Region>()
    for (component in components) {
        componentCheck.checkSpelling(component)
    }
    val incorrectRegions: MutableList<Region> = ArrayList()
    incorrectRegions.addAll(componentCheck.spellingDoubleCommaRegions)
    incorrectRegions.addAll(componentCheck.spellingBeginCapitalRegions)
    incorrectRegions.addAll(componentCheck.spellingWhiteSpacesRegions)
    println("Incorrect components:")
    for (component in incorrectRegions) {
        println(component.description)
    }
    val set: MutableSet<RegionImpl> = HashSet()
    set.add(RegionImpl(regions[1], assignedNumbers[0]))
    set.add(RegionImpl(regions[1], assignedNumbers[0]))
    set.add(RegionImpl(regions[1], assignedNumbers[2]))
    println("Similar components: " + RunSpellingCheck.findSimilarRegions(components, set))
}
