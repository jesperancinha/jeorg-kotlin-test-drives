package org.jesperancinha.xml.adder.instruction

/**
 * Created by joaofilipesabinoesperancinha on 18-02-16.
 */
class XmlAdderAddAttributeManager {
    internal val xmlAdderInstructionArrayMap: MutableMap<String, XmlAdderInstruction> = HashMap()
    fun addAddAttribute(name: String?, value: String?, xpath: String?) = name?.let {
        var instruction = xmlAdderInstructionArrayMap[xpath]
        if (instruction == null) {
            instruction = XmlAdderInstruction(null, HashMap(), null, HashSet(), null, null)
            instruction.addAddAttribute(name, value)
            xpath?.let { xmlAdderInstructionArrayMap[xpath] = instruction }
        } else {
            instruction.addAddAttribute(name, value)
        }
    }

    fun addRemoveAttribute(name: String?, xpath: String?) {
        var instruction = xmlAdderInstructionArrayMap[xpath]
        if (instruction == null) {
            instruction = XmlAdderInstruction(null, HashMap(), null, HashSet(), null, null)
            instruction.addDeleteAttribute(name)
            xpath?.let { xmlAdderInstructionArrayMap[xpath] = instruction }
        } else {
            instruction.addDeleteAttribute(name)
        }
    }

    fun getXmlAdderInstructionArrayMap(): Map<String, XmlAdderInstruction> {
        return xmlAdderInstructionArrayMap
    }
}