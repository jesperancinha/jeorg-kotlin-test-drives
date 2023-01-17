package org.jesperancinha.string.paradigms.nowrapper.expression

import java.util.*

object RunSpellingCheck {
    @JvmStatic
    fun main(args: Array<String>) {
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
        incorrect.addAll(check.getSpellingDoubleCommaRegions())
        incorrect.addAll(check.getSpellingBeginCapitalRegions())
        incorrect.addAll(check.getSpellingWhiteSpacesRegions())
        println("Incorrect attributes: $incorrect")
        val componentCheck = RegionSpellingCheck()
        for (component in components) {
            componentCheck.checkSpelling(component)
        }
        val incorrectRegions: MutableList<RegionImpl?> = ArrayList()
        incorrectRegions.addAll(componentCheck.getSpellingDoubleCommaRegions())
        incorrectRegions.addAll(componentCheck.getSpellingBeginCapitalRegions())
        incorrectRegions.addAll(componentCheck.getSpellingWhiteSpacesRegions())
        println("Incorrect components:")
        for (component in incorrectRegions) {
            println(component!!.description)
        }
        val set: MutableSet<RegionImpl> = HashSet()
        set.add(RegionImpl(regions[1], assignedNumbers[0]))
        set.add(RegionImpl(regions[1], assignedNumbers[0]))
        set.add(RegionImpl(regions[1], assignedNumbers[2]))
        println("Similar components: " + findSimilarRegions(components, set))
    }

    fun findSimilarRegions(
        componentsA: Collection<RegionImpl>,
        componentsB: Collection<RegionImpl>
    ): Set<RegionImpl?> {
        var setToReturn: MutableSet<RegionImpl?> = HashSet()
        setToReturn = similarElementsIteration(componentsA, componentsB, setToReturn)
        setToReturn = similarElementsIteration(componentsB, componentsA, setToReturn)
        println(Arrays.deepToString(setToReturn.toTypedArray()))
        return setToReturn
    }

    /**
     *
     * @param regionA
     * @param regionB
     * @param setToReturn
     * The similarities between region A and region B
     * @return
     */
    fun similarElementsIteration(
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
                            if (null == rootValue || null == toValue || rootValue != toValue) {
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