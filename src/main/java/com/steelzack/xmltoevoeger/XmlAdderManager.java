package com.steelzack.xmltoevoeger;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by joaofilipesabinoesperancinha on 16-02-16.
 */
public class XmlAdderManager {

    private List<String> xpathArrayList = new ArrayList<String>();

    public XmlAdderManager() {
    }

    public void addXpathMatch(String xpathMatch)
    {
        xpathArrayList.add(xpathMatch);
    }
}
