package com.steelzack.xmladder;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by joaofilipesabinoesperancinha on 16-02-16.
 */
public class XmlAdderManager {

    private List<XmlAdderInstruction> xpathArrayList = new ArrayList<XmlAdderInstruction>();

    public XmlAdderManager() {
    }

    public void addXpathMatch(XmlAdderInstruction xpathMatch) {
        xpathArrayList.add(xpathMatch);
    }
}
