package com.steelzack.xmltoevoeger;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by joaofilipesabinoesperancinha on 16-02-16.
 */
public class XmlToevoegerManager {

    private List<String> xpathArrayList = new ArrayList<String>();

    public XmlToevoegerManager() {
    }

    public void addXpathMatch(String xpathMatch)
    {
        xpathArrayList.add(xpathMatch);
    }
}
