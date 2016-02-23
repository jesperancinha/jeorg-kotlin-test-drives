package com.steelzack.xmladder.instruction;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * Created by joaofilipesabinoesperancinha on 18-02-16.
 */
public class XmlAdderAddAttributeManager {

    private Map<String, XmlAdderInstruction> xmlAdderInstructionArrayMap = new HashMap<>();

    public void addAddAttribute(String name, String value, String xpath) {
        XmlAdderInstruction instruction = xmlAdderInstructionArrayMap.get(xpath);
        if (instruction == null) {
            instruction = new XmlAdderInstruction(null, new HashMap<>(), null, new HashSet<>(), null, null);
            instruction.addAddAttribute(name, value);
            xmlAdderInstructionArrayMap.put(xpath, instruction);
        } else {
            instruction.addAddAttribute(name, value);
        }
    }

    public void addRemoveAttribute(String name, String xpath) {
        XmlAdderInstruction instruction = xmlAdderInstructionArrayMap.get(xpath);
        if (instruction == null) {
            instruction = new XmlAdderInstruction(null, new HashMap<>(), null, new HashSet<>(), null, null);
            instruction.addDeleteAttribute(name);
            xmlAdderInstructionArrayMap.put(xpath, instruction);
        } else {
            instruction.addDeleteAttribute(name);
        }
    }

    public Map<String, XmlAdderInstruction> getXmlAdderInstructionArrayMap() {
        return xmlAdderInstructionArrayMap;
    }
}
