package com.steelzack.xmladder.instruction;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by joaofilipesabinoesperancinha on 18-02-16.
 */
public class XmlAdderAddAttributeManager {

    private Map<String, XmlAdderInstruction> xmlAdderInstructionArrayMap = new HashMap<>();

    public void addAttribute(String name, String value, String xpath) {
        XmlAdderInstruction instruction = xmlAdderInstructionArrayMap.get(xpath);
        if (instruction == null) {
            instruction = new XmlAdderInstruction(null, new HashMap<>(), null, null, null);
            xmlAdderInstructionArrayMap.put(xpath, instruction);
        } else {
            instruction.addAttribute(name, value);
        }
    }
}
